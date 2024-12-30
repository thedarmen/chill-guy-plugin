# **Meme Window Plugin for IntelliJ IDEA**

## **Overview**

The **Meme Window Plugin** is an IntelliJ IDEA plugin designed to create an interactive and fun experience. The plugin introduces a tool window called **Meme Window**, where a meme dynamically resizes and follows the cursor based on its entry direction.

## **Features**

* Adds a custom **Meme Window** to IntelliJ IDEA.  
* Displays a meme that dynamically resizes and follows the cursor.  
* Logic-based resizing depending on cursor movement (horizontal or vertical).  
* Supports customizable meme images.

## **Requirements**

* **IntelliJ IDEA**: Version 2023.3.4 (Ultimate Edition or Community Edition).  
* **JDK**: Version 17 or higher.  
* **Gradle**: Version 8.5 or compatible.

## **Installation**

### **1\. Install the Plugin**

1. Download the plugin `.zip` file from the Releases.  
2. Open IntelliJ IDEA.  
3. Navigate to **Settings \> Plugins \> ⚙ \> Install Plugin from Disk**.  
4. Select the downloaded `.zip` file.  
5. Restart IntelliJ IDEA.

### **2\. Build from Source (Optional)**

1. Clone the repository:  
   git clone \<repository-link\>  
2. Navigate to the project directory:  
   cd \<project-folder\>  
3. Build the plugin using Gradle:  
   ./gradlew buildPlugin  
4. Locate the `.zip` file in `build/distributions` and install it as described above.

## **Usage**

1. Open IntelliJ IDEA.  
2. Navigate to **View \> Tool Windows \> Meme Window**.  
3. Interact with the meme by moving the cursor over the window. The meme dynamically resizes and follows the cursor.

### **Customizing the Meme**

1. Replace the `meme.png` file in the plugin's source directory.  
2. Ensure the new image is placed in `src/main/resources`.  
3. Rebuild the plugin as described above.

## **How It Works**

* **Dynamic Resizing**: The meme resizes based on cursor movement and its entry direction.  
  * Horizontal movement increases or decreases size depending on entry from left or right.  
  * Vertical movement adjusts size for entry from top or bottom.  
* **Smooth Interaction**: The meme always follows the cursor for seamless user experience.

## **Technical Details**

* **Languages**: Kotlin and Java (for core logic).  
* **Libraries**: Built-in Java Swing for GUI development.  
* **Build Tool**: Gradle.

## **Contributing**

Contributions are welcome\! Follow these steps:

1. Fork the repository.  
2. Create a new branch:  
   git checkout \-b feature-name  
3. Commit your changes and push the branch:  
   git push origin feature-name  
4. Create a Pull Request.

## **License**

This plugin is licensed under the MIT License. See the LICENSE file for details.

## **Contact**

For questions or issues, feel free to contact:

* **Author**: Darmen Mellat  
* **Email**: \[darmen.mellat@gmail.com\]

