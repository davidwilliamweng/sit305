###########Project structure description
├── .gradle                                   // Temporary file of gradle
├── .idea							          // Temporary file of android studio
├── build							          // Temporary file of source code compilation
├── gradle							          // Configuration file of gradle
├── .gitignore                                // Git submits rule files
├── app                       		          // application name
│   └── assets                    		      // resources
│   └── build                    		      // Temporary file of source code compilation
│   └── libs                    		      // Jar
│	└── src                     		      // Source code
│		└── main                		
│			└── java            		
│			└────── com                       // package
│			└───────── david
│			└──────────── calendaralarm
│			└────────────────── app           // Global settings 
│			└────────────────── data          // Data Model
│			└────────────────── schedule      //Background Service
│			└────────────────── tabs          // UI
│			└────────────────── utils	      // Tools
│			└── res            		          // resources
│			└── AndroidManifest.xml           // configuration file
│   ├── release                    		      // Packing Generation File
│   ├── build.gradle            		      // 
│   ├── gradle.properties       		      // 
│   ├── local.properties        		      // 