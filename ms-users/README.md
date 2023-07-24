# Template with hexagonal architech

Spring boot application with hexagonal arquitect using diferent layers for separate the code. Using database h2.

## How can i use the code?

- Download the code from repository.
- Open the code with the prefered IDE, por example IntellID.
- Run the aplication (The main is located in launcher module inside application).

## Available endpoints
The application has the following endpoints:

### ``` [GET]/book/books ```

Get the list de books that exits in database.

### ``` [GET]/book/books/{id} ```

Get the book with id indicated in the path. Example: /book/book/5

### ``` [DELETE]/book/delete/{id} ```

Delete the book with id indicated in the path . Example: /book/book/5

### ``` [POST]/book/add ```

Add a book, passing by json the book data. 

### ``` [PUT]/book/update ```

Add a book, passing by json the book data. 

## Libraries used

- CheckStyle: To validate the code, using the [Google Checkstyle](https://github.com/checkstyle/checkstyle/blob/master/src/main/resources/google_checks.xml)
- JaCoCo: To generate test report (its can see in module jacoco-report)






