1.	When registering for an Organizer Account, a registration code is required. When prompted, enter this registration code, which is set to 123456.
2.	The Serialized files containing instances of AccountManager, EventManager, ConversationManager, FriendManager, and SignupManager are by default located in the root of the working directory.
3.	Upon initiation of the program, the gateway will attempt to read existing Serialized files at the default file path. If no files are found, the gateway will automatically create new empty instances of each, and it will output a notification to the console.
4.	To properly record changes, the current session must be logged out properly. After each logout of an Account, the gateway will immediately write/overwrite each instance of the managers to their respective Serialized files. If the program is manually stopped or throws an unhandled exception, any changes made from the current login session of an Account will be lost.
5.	It is assumed that all events must start at the hour and last for only one hour. The program will only check if the starting time is between 9 AM to 4 PM.
6.	An account that is logged in is able to add itself in its own contact list, as well as message itself.
7.	Contact lists are asymmetrical; a user account adding another account to its contact list does not add itself to the contact list of that other account. Our implementation is similar to phone contacts instead of facebook friends.
8.	An account’s username is its unique identifier; no two accounts can share the same username, and usernames cannot be changed. The same applies for the ID of events and messages.
9.	A new talk can only be added by an organizer if the specified location of the talk has already been added. I.e. a talk can only be added if the specified location of the talk is valid.
10.	All talk schedules only display talks in the future. Similarly, one cannot cancel, leave, or reschedule a talk whose start time has already passed.
11.	UML Diagram is included in our Phase1 folder
12.	Successful registration of a new account from the start menu will return you to the beginning of the start menu. Afterwards you may log into to the new account.

