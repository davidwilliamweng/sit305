###########Project structure description
├── app                       		          								// application name
│   └── assets                    		      								// resources
│   └── build                    		      								// Temporary file of source code compilation
│   └── libs                    		      								// Jar
│	└── src                     		      								// Source code
│		└── main                		
│			└── java            		
│			└────── com                       								// Package name
│			└───────── david
│			└──────────── calendaralarm
│			└────────────────── app           								// Global settings 
│			└────────────────── data          								// Data Model
│			└────────────────── schedule      								// Background Service
│			└────────────────── tabs          								// UI
│			└─────────────────────── addalarm 								// About user interface of alarm
│			└────────────────────────────── alarm
│			└──────────────────────────────── AlarmFragment.java            // The user interface of alarm
│			└──────────────────────────────── AddEditAlarmActivity.java     // The user interface of set an alarm
│			└─────────────────────── calendar                               // About user interface of calendar calendar
│			└──────────────────────────────── CalendarFragment.java         // The user interface of calendar alarm
│			└────────────────── utils	      								// Tools
│			└── res            		          								// Some resources
│			└───── anim            		          						    // Animation resource file
│			└───── color            		          						// Color resource file
│			└───── drawable            		          						// Shape resource file
│			└───── layout            		          						// Layout resource file
│			└───── values            		          						// String values resource file
│			└── AndroidManifest.xml           								// Entry files for android applications