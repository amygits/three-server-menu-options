# 3 Server w Menu Options
## Description
This application has 3 different run methods with different server implementations.  The base program allows users to add strings to a list using JSON objects and then gives them options to remove a string, reverse a string, display the list, or quit.  Multiple users joining the same client will get access to the same list

1. runTask1 - Simple server that supports a single client
2. runTask2 - Threaded server that supports multiple clients
3. runTask3 - Thread pool server that suppoorts a limited amount of clients

## Protocol

### Requests
request: { "selected": <int: 1=add, 2=remove, 3=display, 4=count, 5=reverse,
0=quit>, "data": <thing to send>}

  add: data <string>
  remove: data <int>
  display: no data
  count: no data
  reverse: data <int>
  quit: no data

### Responses

sucess response: {"type": <"add",
"remove", "display", "count", "reverse", "quit"> "data": <thing to return> }

type <String>: echoes original selected from request (e.g. if request was "add", type will be "add")
data <string>: add = new list, remove = removed element, display = current list, count = num elements, reverse = new list


error response: {"type": "error", "message"": <error string> }
Should give good error message if something goes wrong which explains what went wrong


## How to run the program
### Terminal
Base Code, please use the following commands: 
  
For Simple Server, run `"gradle runTask1 -Pport=9099 -q --console=plain"`
  
For Threaded Server(Unbound), run `"gradle runTask2 -Pport=9099 -q --console=plain"`
  
For Threaded Server Pool (Bound), run `"gradle runTask3 -Pport=9099 -q --console=plain"`

For Client, run `"gradle runClient -Phost=localhost -Pport=9099 -q --console=plain"`
  
  
### Requirements Fulfilled
* Performer is more "interesting":
  * Add <string> - Adds a string to list
  * Remove <int> - Removes elemeent at at given index and displays list
  * Displays - Displays current list of <string>
  * Count - Returns number of elements in the list and displays it
  * Reverse <int> - Reverse the string at a given index and displays new list
* Multi-Threaded Server (Task2)
  * Allows unbounded connections to the server
  * String list is properly managed
* Bounded Thread Pool (Task3)
  * Allows set number of incoming connections at any given time
  * String list is properly managed
 
### [ScreenCast demo](https://youtu.be/_zzMCs_QPNw)



