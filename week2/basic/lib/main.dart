import 'package:flutter/material.dart';
//const Color.fromARGB(255, 233, 208, 100),
//const Color.fromARGB(255, 153, 243, 156)
// const Color.fromARGB(255, 157, 183, 204),


void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: Text('Combined Row and Column Layout'),
        ),
        body: SingleChildScrollView(
          child: Column(
            children: <Widget>[
              // Column Layout Section
              Padding(
                padding: const EdgeInsets.all(8.0),
                child: Text(
                  'Column Layout Example',
                  style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
                ),
              ),
              Column(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.center,
                children: <Widget>[
                  Container(
                    padding: EdgeInsets.all(20.0),
                    color:  Color.fromARGB(255, 233, 208, 100),
                    child: Text(
                      'Item 1',
                      style: TextStyle(fontSize: 24),
                    ),
                  ),
                  Container(
                    padding: EdgeInsets.all(20.0),
                    color: const Color.fromARGB(255, 153, 243, 156),
                    child: Text(
                      'Item 2',
                      style: TextStyle(fontSize: 24),
                    ),
                  ),
                  Container(
                    padding: EdgeInsets.all(20.0),
                    color: const Color.fromARGB(255, 157, 183, 204),
                    child: Text(
                      'Item 3',
                      style: TextStyle(fontSize: 24),
                    ),
                  ),
                ],
              ),

              SizedBox(height: 40),

              // Row Layout Section
              Padding(
                padding: const EdgeInsets.all(8.0),
                child: Text(
                  'Row Layout Example',
                  style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
                ),
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                children: <Widget>[
                  Container(
                    padding: EdgeInsets.all(20),
                    color: const Color.fromARGB(255, 233, 208, 100),
                    child: Text('Item 1'),
                  ),
                  Container(
                    padding: EdgeInsets.all(20),
                    color: const Color.fromARGB(255, 153, 243, 156),
                    child: Text('Item 2'),
                  ),
                  Container(
                    padding: EdgeInsets.all(20),
                    color: const Color.fromARGB(255, 157, 183, 204),
                    child: Text('Item 3'),
                  ),
                ],
              ),
            ],
          ),
        ),
      ),
    );
  }
}
