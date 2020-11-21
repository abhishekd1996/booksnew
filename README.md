## Book Store
Application to solve below use cases

> *  Add a Book to the store. Mandatory information is ISBN, Title, Author, price. Store
 might have multiple copies of the same book. 
> *  Search book based on ISBN/Title/Author. Title and Author searches must allow for
 partial matches too. 
> * Search media coverage about a book, given its ISBN. To find media coverage about the
 book, use the public rest API https://jsonplaceholder.typicode.com/posts . If the title of the
 book is present in any of the posts’ title or body, then it’s a match. For all posts that match,
 return the title of the posts in this media coverage API. 
> *  Buy a book. If book count becomes zero, add a book as well. No payment related
 processing is required.

### Requirements
* Java 8
* Gradle
* Docker

### Build Instructions
Run below command for building the app

    ./gradlew build

Run below command to create Docker image

    docker build -t book-store .

### Deployment
Run below command to start the docker containers.

    docker-compose -f bookstore-docker-compose.yml up -d
    
This command will starts the 2 containers.
* Database - Mariadb
* Webapp

### Accessing the Application
App will be exposed on port: `1212`

Here are list of API's exposed form the App
#### Books
##### Create Book
    url: /api/v1/books
    method: POST
    Body: {
            isbn: string,
            title: string,
            price: double,
            author: {
              name: string
            }
          }

##### Fetch Books
    url: /api/v1/books
    method: GET
    Request Params: 
        isbn: string - optional
        title: string - optional
        author: string - optional
Returns matching all books for given search filter.
##### Fetch Book Details
    url: /api/v1/books/{bookIsbn}
    method: GET
##### Fetch Book Media Coverage
    url: /api/v1/books/{bookIsbn}/coverage
    method: GET
 
#### Inventory
##### Add stock
    url: /api/v1/inventory
    method: POST
    body: {
            bookIsbn: string
            stock: number
          }
##### Fetch stock
    url: /api/v1/inventory
    method: GET
##### Fetch stock for book
    url: /api/v1/inventory/{bookIsbn}
    method: GET
#### Orders
##### Create Order
    url: /api/v1/orders
    method: POST
    body: {
            bookIsbn: string,
            quantity: number
          } 

    
