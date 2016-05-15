# Get your guide reviews browser
This is demo test application

## **Install notes** ##

1. git clone [https://github.com/gygTask2016/gygReviewBrowser.git](https://github.com/gygTask2016/gygReviewBrowser.git "https://github.com/gygTask2016/gygReviewBrowser.git")
2. Import project to Android studio

## External libraries ##
In instructions was not specified whether or not I am allow to use external libraries.
To speed up development I am using external libraries only in networking.
Libraries I am using in project are:

- [http://square.github.io/retrofit/](http://square.github.io/retrofit/ "Rertrofit")
- [http://square.github.io/okhttp/](http://square.github.io/okhttp/ "OkHttp")
- [https://github.com/google/gson](https://github.com/google/gson "Gson")


## Submit review ##
Payload to submit review

    {   
		"tour_id":5465484,
        "user_token":"454ABSL65asdf",
        "review_message":"Test message",
		"rating":2.0
	}

- **tour_id** id of tour where review belongs. Should be received from endpoint.
- **user_token** session token of user. Should be generated and received from endpoint when login. 
- **review_message** message content
- **rating** rating content

**Possible responses**

Http code 200 if success, other standard https responses should be also handled. 
Possible error response could be "token expiration" or "wrong token".
User should login again in this case

*P.S: If you press the floating button with letter icon, submit review dialog is shown*. 





