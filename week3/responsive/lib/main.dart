import 'package:flutter/material.dart';

void main() => runApp(LoginApp());

class LoginApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Responsive Login',
      home: LoginPage(),
    );
  }
}

class LoginPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: LayoutBuilder(
        builder: (context, constraints) {
          // Check the screen width and adjust the layout accordingly
          if (constraints.maxWidth > 600) {
            // For larger screens (like tablets or web)
            return Row(
              children: [
                Expanded(
                  child: Container(
                    color: Colors.blueGrey[50],
                    child: Center(
                      child: Text(
                        'Welcome Back! BVRIT HYDERABAD',
                        style: TextStyle(
                          fontSize: 36,
                          fontWeight: FontWeight.bold,
                          color: Colors.blue,
                        ),
                      ),
                    ),
                  ),
                ),
                Expanded(
                  child: loginForm(),
                ),
              ]  );
          } else {
            // For smaller screens (like phones)
            return loginForm();
          }
        },
      ),
    );
  }

  Widget loginForm() {
    return Padding(
      padding: const EdgeInsets.all(16.0),
      child: Center(
        child: SingleChildScrollView(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text(
                'Login',
                style: TextStyle(
                  fontSize: 32,
                  fontWeight: FontWeight.bold,
                ),
              ),
              SizedBox(height: 30),
              TextField(
                decoration: InputDecoration(
                  labelText: 'Email',
                  border: OutlineInputBorder(),
                ),
              ),
              SizedBox(height: 20),
              TextField(
                obscureText: true,
                decoration: InputDecoration(
                  labelText: 'Password',
                  border: OutlineInputBorder(),
                ),
              ),
              SizedBox(height: 30),
              SizedBox(
                width: double.infinity,
                child: ElevatedButton(
                  onPressed: () {
                    // Add your login logic here
                  },
                  child: Text('Login'),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}