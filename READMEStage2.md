# StocksTerminal - SE_Project

![](https://media.istockphoto.com/id/1148438339/photo/data-structure-and-information-tools-for-networking-business.jpg?b=1&s=170667a&w=0&k=20&c=d7hRfsaF-CZ3F8CblVGZa26uN3veIJJRcsny0ZIxZ9M=)

---

**Software Engineering Project:** CLI Stock Data Manager - Stage 2

**Collaborators:** Max Franklin `&&` Noam Ben Simon

---

**Data Structures:**

We will use a combination of custom classes and data structures to use and store our program.

We will also have a general "brain" that is used for the call and response of commands and requests.


**Custom Classes**

 - "Stock" - when a new stock ticker symbol is called, a new class of type "Stock" will be instantiated, with the symbol as the main identifier.
    - this stock object will also be the main holder of data for that given stock. For example, "Stock" of type "AAPL" will hold the information requested for Apple's stock, and will refresh according to requests by the user.

 - "Logic" - the brains of the operation. Will do all necessary calls to the "Data" class and organize as well as instantiate stocks and infromation calls.

 - "Data" - Holds the references to the objects and calls within it. Added mostly to clean up and simplify the work of the "Logic" class, but will also serve an important function in hosting and updating the data calls.

 **Data Structures**

 *For "Stock"*
 - String: the symbol of the stock.
 - String(s): each request will have its own associated String for that information returned.
    - This will be concatenated (or not) and returned by request of the user.
 - Long: holds the last time accessed/used for a given stock.

*For "Logic"*
 - String Array: all things passed in from args. This will be broken down into the following:
     - String Array: stores the Stock symbols passed in.
     - String Array: stores the arguments/commands passed in.
     - Character Array: stores the array of flags for the actual requests of a given command (if there exist any).
 - String: the output will be formatted and concatenated into one string which will then be printed at CMD.

*For "Data"*
  - MinHeap: storage for cached stocks, which will cap at 10, and then remove the least-recently used stock and remove it from the cache.