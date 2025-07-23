import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: MediaQueryExample(),
    );
  }
}

class MediaQueryExample extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    // Get the screen width using MediaQuery
    double screenWidth = MediaQuery.of(context).size.width;

    // Set different properties based on screen width
    double fontSize = screenWidth < 600 ? 28 : 24;
    Color backgroundColor = screenWidth < 700 ? const Color.fromARGB(255, 110, 181, 240) : const Color.fromARGB(255, 138, 230, 141);

    return Scaffold(
      backgroundColor: backgroundColor,
      appBar: AppBar(
        title: Text('MediaQuery Example'),
      ),
      body: Center(
        child: Text(
          'Screen width: $screenWidth\nThis text is responsive!',
          textAlign: TextAlign.center,
          style: TextStyle(fontSize: fontSize, color: const Color.fromARGB(255, 173, 135, 243)),
        ),
      ),
    );
  }
}