1.	The Serialized files containing instances of AccountManager, EventManager, ConversationManager, ContantManager,
SignupManager and RequestManager by default located in the root of the working directory.

2.	Upon initiation of the program, the gateway will attempt to read existing Serialized files at the default file path.
If no files are found, the gateway will automatically create new empty instances of each, and it will output a
notification to the console.

3.	To properly record changes, the current session must be logged out properly. After each logout of an Account,
the gateway will immediately write/overwrite each instance of the managers to their respective Serialized files.
If the program is manually stopped or throws an unhandled exception, any changes made from the current login session
of an Account will be lost.

4.	When registering for an Organizer Account, a registration code is required. When prompted,
enter this registration code, which is set to 123456.

5.	An account that is logged in is able to add itself in its own contact list, as well as message itself.

6. An additional account type is the VIP attendee. They are are subclass of Attendee and are able to do everything that
an Attendee can do. However, one difference is that they are able to attend VIP-only events, while normal Attendees
cannot. Additionally, VIP-attendee creation requires a registration code, 123456.

7. Attendees (And VIP attendees) can now send requests. Organizers can view them and resolve them. For more detail see
the new menu options for Attendee, VIP attendee and organizer.

8.	Contact lists are asymmetrical; a user account adding another account to its contact list does not add itself to
the contact list of that other account. Our implementation is similar to phone contacts instead of facebook friends.

9.	An account’s username is its unique identifier; no two accounts can share the same username, and usernames cannot
be changed. The same applies for the ID of events and messages.

10.	It is assumed that all events must start at the hour and last for only one hour. The program will only check if the
starting time is between 9 AM to 4 PM.

11. There are three types of events: Talk, Panel discussion, and Networking event. A talk requires one speaker, a
panel discussion requires two speakers, and a networking event requires none.

12. Locations have characteristics, and events have requirements. Some examples that both have are capacity, chairs, tables,
internet, etc. Events also potentially have a VIP restriction, which could make them "VIP only events".

13.	A new event can only be added by an organizer if the specified location of the event has already been added.
I.e. a event can only be added if the specified location of the talk is valid.

14.	All event schedules only display events in the future. Similarly, one cannot cancel, leave, or reschedule an event
 whose start time has already passed.

15. Events can now be cancelled by Organizers.

16.	UML Diagram is included in our Phase2 folder

17. we have introduced restrictions to messaging based on contact lists. To first start a new
conversation with someone, you must have added them as a contact. The recipient of that first message can still
reply to the sender without having as a contact though.

18.	Successful registration of a new account from the start menu will return you to the beginning of the start menu.
Afterwards you may log into to the new account.

19. All account types can download a html file containing a table of all events schedules and some information
about them.

20. Have fun using our program!
