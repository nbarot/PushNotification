'use strict';

//importScripts('scripts/indexdbwrapper.js');

var YAHOO_WEATHER_API_ENDPOINT = 'https://dharma.tripcase.com/developer_api/trips/4052543.json?access_token=fa83008165accdd6646542d303ac9fdb8f64e72e9e42a70722727e94037b501a';
var KEY_VALUE_STORE_NAME = '123key-value-store123';

var idb;

var ffpNumber='n/a';

var idbDatabase;
var IDB_VERSION = 1;
var STOP_RETRYING_AFTER = 86400000; // One day, in milliseconds.
var STORE_NAME = 'db';

var indexedDBOpenRequest;

// This is basic boilerplate for interacting with IndexedDB. Adapted from
// https://developer.mozilla.org/en-US/docs/Web/API/IndexedDB_API/Using_IndexedDB
function openDatabaseAndReplayRequests() {
  
	if(!idbDatabase || !indexedDBOpenRequest) {
		indexedDBOpenRequest = indexedDB.open(STORE_NAME, IDB_VERSION);
	
	  // This top-level error handler will be invoked any time there's an IndexedDB-related error.
	  indexedDBOpenRequest.onerror = function(error) {
	    console.error('IndexedDB error:', error);
	  };
	
	  // This should only execute if there's a need to create a new database for the given IDB_VERSION.
	  indexedDBOpenRequest.onupgradeneeded = function() {
	    this.result.createObjectStore(KEY_VALUE_STORE_NAME);
	  };
	
	  // This will execute each time the database is opened.
	  indexedDBOpenRequest.onsuccess = function() {
	    idbDatabase = this.result;
	  };
	}
}



//Helper method to get the object store that we care about.
function getObjectStore(storeName, mode) {
	openDatabaseAndReplayRequests();
return idbDatabase.transaction(storeName, mode).objectStore(storeName);
}

function add(key, value) {
	console.log("Adding key:"+key+", value:"+value);
	getObjectStore(KEY_VALUE_STORE_NAME, 'readwrite').put(value,key);
}


function getValueFromDB(key) {	
	var ffpFromDB = 'na';
	
	getObjectStore(KEY_VALUE_STORE_NAME, 'readonly').get(key).onsuccess = function(event) {
		  //alert("Name for SSN 444-44-4444 is " + event.target.result.name);
		  console.log("new impl, Got result from db:", event.target.result);
		  ffpFromDB = event.target.result;
		  return event.target.result;
		};
		
		
	return ffpFromDB;	
	/*var request = getObjectStore(KEY_VALUE_STORE_NAME, 'readonly').get(key);
	request.onerror = function(event) {
		console.log("error while getting value from db");
	  // Handle errors!
	};
	
	request.onsuccess = function(event) {
	  // Do something with the request.result!
		ffpFromDB = request.result;
		console.log("valuefromdb -> Got data from db:", ffpFromDB);	  
	};
	return ffpFromDB;*/
	
}

// avoid opening idb until first call
function getIdb() {
  if (!idb) {
    idb = new IndexDBWrapper(KEY_VALUE_STORE_NAME, 1, function(db) {
      db.createObjectStore(KEY_VALUE_STORE_NAME);
    });
  }	
  return idb;
}

function showNotification(title, body, icon, data) {
  var notificationOptions = {
    body: body,
    icon: icon ? icon : 'images/touch/chrome-touch-icon-192x192.png',
    tag: 'MFly-push-demo-notification',
    data: data
  };
  if (self.registration.showNotification) {
    self.registration.showNotification(title, notificationOptions);
    return;
  } else {
    new Notification(title, notificationOptions);
  }
}

self.addEventListener('message', function(event) {
	  console.log('Handling message event:', event);
	  console.log('Got FFP:', event.data.command);
	  ffpNumber = event.data.command;
	  //getIdb().put(KEY_VALUE_STORE_NAME, "FFP", ffpNumber);
	  add('ffp', ffpNumber);
	  var ffpFromDB = getValueFromDB('ffp');
	  var dbffp = ffpFromDB; //get('ffp');
	  console.log("FFP from db:", dbffp);
});

self.addEventListener('push', function(event) {
  console.log('Received a push message', event);

  
  //console.log(getIdb().get(KEY_VALUE_STORE_NAME, "FFP"));
  // Since this is no payload data with the first version
  // of Push notifications, here we'll grab some data from
  // an API and use it to populate a notification
  event.waitUntil(
    fetch(YAHOO_WEATHER_API_ENDPOINT).then(function(response) {
      if (response.status !== 200) {
        console.log('Looks like there was a problem. Status Code: ' +
          response.status);
        // Throw an error so the promise is rejected and catch() is executed
        throw new Error();
      }

      // Examine the text in the response
      return response.json().then(function(data) {
    	  console.log('Data = ', JSON.stringify(data));
          if (data.items.count === 0) {
            // Throw an error so the promise is rejected and catch() is executed
            throw new Error();
          }
        
    	getObjectStore(KEY_VALUE_STORE_NAME, 'readonly').get("ffp").onsuccess = function(event) {
  		  //alert("Name for SSN 444-44-4444 is " + event.target.result.name);
  		  console.log("new impl, Got result from db:", event.target.result);
  		  var ffpFromDB = event.target.result;
  		  //return event.target.result;
  		  
          console.log(" inside notification Got ffp from db",ffpFromDB);
          var title = 'Your flight has been delayed. Please check the latest departure time.';
          var message = data.items[0].air_reservation.flight.flight_number;
          console.log(message);
          var icon = data.items[0].destination_card_image_url ||
            'images/touch/chrome-touch-icon-192x192.png';
          var notificationTag = 'MFly-push-demo-notification';

          // Add this to the data of the notification
          var urlToOpen = data.items[0].air_reservation.seat_map_url;

          if (!Notification.prototype.hasOwnProperty('data')) {
            // Since Chrome doesn't support data at the moment
            // Store the URL in IndexDB
            //getIdb().put(KEY_VALUE_STORE_NAME, notificationTag, urlToOpen);
          }

          var notificationFilter = {
            tag: 'MFly-push-demo-notification'
          };

          var notificationData = {
            url: urlToOpen
          };

          if (self.registration.getNotifications) {
            return self.registration.getNotifications(notificationFilter)
              .then(function(notifications) {
                if (notifications && notifications.length > 0) {
                  // Start with one to account for the new notification
                  // we are adding
                  var notificationCount = 1;
                  for (var i = 0; i < notifications.length; i++) {
                    var existingNotification = notifications[i];
                    if (existingNotification.data &&
                      existingNotification.data.notificationCount) {
                      notificationCount += existingNotification.data.notificationCount;
                    } else {
                      notificationCount++;
                    }
                    existingNotification.close();
                  }
                  message = 'You have ' + notificationCount +
                    ' flight updates.';
                  notificationData.notificationCount = notificationCount;
                }

                return showNotification(title, message, icon, notificationData);
              });
          } else {
            return showNotification(title, message, icon, notificationData);
          }
  		  
  		};

        
        //var ffpFromDB = getValueFromDB('ffp');
      });
    }).catch(function(err) {
      console.error('Unable to retrieve data', err);
      var ffpFromDB = getValueFromDB('ffp');
      var title = 'An error occured';
      var message = 'We were unable to get the information for this ' +
        'push message... ffp:->'+ffpFromDB;

      return showNotification(title, message);
    })
  );
});

self.addEventListener('notificationclick', function(event) {
  console.log('On notification click: ', event);

  if (Notification.prototype.hasOwnProperty('data')) {
    console.log('Using Data');
    var url = event.notification.data.url;
    event.waitUntil(clients.openWindow(url));
  } else {
    /*event.waitUntil(getIdb().get(KEY_VALUE_STORE_NAME, event.notification.tag).then(function(url) {
      // At the moment you cannot open third party URL's, a simple trick
      // is to redirect to the desired URL from a URL on your domain
      var redirectUrl = '/redirect.html?redirect=' +
        url;
      return clients.openWindow(redirectUrl);
    }));*/
  }
});