[STILL UNDER CONSTRUCTION]

API DOC: https://jsonplaceholder.typicode.com/
API LINK: https://jsonplaceholder.typicode.com/

Fitur yang mau dibuat
1. LIST POSTS (/posts)
	METHOD: GET	
	URL: https://jsonplaceholder.typicode.com/posts
	Respond:
	[
	  {
		"userId": 1,
		"id": 1,
		"title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
		"body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
	  },
	  {
		"userId": 1,
		"id": 2,
		"title": "qui est esse",
		"body": "est rerum tempore vitae\nsequi sint nihil reprehenderit dolor beatae ea dolores neque\nfugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis\nqui aperiam non debitis possimus qui neque nisi nulla"
	  },
	...
	]  
2. DETAIL POST (/posts/{id})
	METHOD: GET	
	URL: https://jsonplaceholder.typicode.com/posts/{id}
	Respond:
	{
	  "userId": 1,
	  "id": 1,
	  "title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
	  "body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
	}