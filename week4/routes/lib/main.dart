// import 'package:flutter/material.dart';

// void main() {
//   runApp(MyApp());
// }

// class MyApp extends StatelessWidget {
//   @override
//   Widget build(BuildContext context) {
//     return MaterialApp(
//       debugShowCheckedModeBanner: false,
//       title: 'Flutter Navigation',
//       // Define named routes here
//       initialRoute: '/', // Set the initial route
//       routes: {
//         '/': (context) => FirstScreen(), // Route for the first screen
//         '/second': (context) => SecondScreen(), // Route for the second screen
//       },
//     );
//   }
// }

// class FirstScreen extends StatelessWidget {
//   @override
//   Widget build(BuildContext context) {
//     return Scaffold(
//       appBar: AppBar(
//         title: Text('First Screen'),
//         backgroundColor: Colors.blue,
//       ),
//       body: Center(
//         child: Column(
//           mainAxisAlignment: MainAxisAlignment.center,
//           children: <Widget>[
//             Text(
//               'Welcome to the First Screen!',
//               style: TextStyle(fontSize: 24, color: Colors.black87),
//             ),
//             SizedBox(height: 30),
//             ElevatedButton(
//               onPressed: () {
//                 // Navigate to the SecondScreen using Navigator.pushNamed
//                 Navigator.pushNamed(context, '/second');
//               },
//               style: ElevatedButton.styleFrom(
//                 backgroundColor: Colors.blueAccent, // Button background color
//                 foregroundColor: Colors.white, // Button text color
//                 padding: EdgeInsets.symmetric(horizontal: 30, vertical: 15),
//                 textStyle: TextStyle(fontSize: 18),
//                 shape: RoundedRectangleBorder(
//                   borderRadius: BorderRadius.circular(10), // Rounded corners
//                 ),
//               ),
//               child: Text('Go to Second Screen'),
//             ),
//           ],
//         ),
//       ),
//     );
//   }
// }

// class SecondScreen extends StatelessWidget {
//   @override
//   Widget build(BuildContext context) {
//     return Scaffold(
//       appBar: AppBar(
//         title: Text('Second Screen'),
//         backgroundColor: Colors.green,
//       ),
//       body: Center(
//         child: Column(
//           mainAxisAlignment: MainAxisAlignment.center,
//           children: <Widget>[
//             Text(
//               'You are on the Second Screen!',
//               style: TextStyle(fontSize: 24, color: Colors.black87),
//             ),
//             SizedBox(height: 30),
//             ElevatedButton(
//               onPressed: () {
//                 // Navigate back to the FirstScreen using Navigator.pop
//                 // When using named routes, pop still works the same way
//                 Navigator.pop(context);
//               },
//               style: ElevatedButton.styleFrom(
//                 backgroundColor: Colors.greenAccent, // Button background color
//                 foregroundColor: Colors.white, // Button text color
//                 padding: EdgeInsets.symmetric(horizontal: 30, vertical: 15),
//                 textStyle: TextStyle(fontSize: 18),
//                 shape: RoundedRectangleBorder(
//                   borderRadius: BorderRadius.circular(10), // Rounded corners
//                 ),
//               ),
//               child: Text('Go Back to First Screen'),
//             ),
//           ],
//         ),
//       ),
//     );
//   }
// }


import 'package:flutter/material.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Flutter Navigation',
      // Define named routes here
      initialRoute: '/', // Set the initial route
      routes: {
        '/': (context) => FirstScreen(), // Route for the first screen
        '/second': (context) => SecondScreen(), // Route for the second screen
      },
    );
  }
}

class FirstScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('First Screen'),
        backgroundColor: Colors.blue,
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              'Welcome to the First Screen!',
              style: TextStyle(fontSize: 24, color: Colors.black87),
            ),
            SizedBox(height: 30),
            ElevatedButton(
              onPressed: () {
                // Navigate to the SecondScreen using Navigator.pushNamed
                Navigator.pushNamed(context, '/second');
              },
              style: ElevatedButton.styleFrom(
                backgroundColor: Colors.blueAccent, // Button background color
                foregroundColor: Colors.white, // Button text color
                padding: EdgeInsets.symmetric(horizontal: 30, vertical: 15),
                textStyle: TextStyle(fontSize: 18),
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(10), // Rounded corners
                ),
              ),
              child: Text('Go to Second Screen'),
            ),
          ],
        ),
      ),
    );
  }
}

class SecondScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Second Screen'),
        backgroundColor: Colors.green,
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(
              'You are on the Second Screen!',
              style: TextStyle(fontSize: 24, color: Colors.black87),
            ),
            SizedBox(height: 30),
            ElevatedButton(
              onPressed: () {
                // Navigate back to the FirstScreen using Navigator.pop
                // When using named routes, pop still works the same way
                Navigator.pop(context);
              },
              style: ElevatedButton.styleFrom(
                backgroundColor: Colors.greenAccent, // Button background color
                foregroundColor: Colors.white, // Button text color
                padding: EdgeInsets.symmetric(horizontal: 30, vertical: 15),
                textStyle: TextStyle(fontSize: 18),
                shape: RoundedRectangleBorder(
                  borderRadius: BorderRadius.circular(10), // Rounded corners
                ),
              ),
              child: Text('Go Back to First Screen'),
            ),
          ],
        ),
      ),
    );
  }
}
