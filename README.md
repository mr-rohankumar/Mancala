# Mancala (SJSU CS 151, FALL 2015)

![Icon](https://i.imgur.com/YVhyc9m.jpg)

## Purpose:
To explore model-view-controller (MVC) architecture and strategy patterns principles by simulating the ancient game of Mancala.

## Implementation:
The Model class maintains the game state and alerts attached listeners. The Logic class controls the model through commands defined by the game rules. The Pit class and the Board class are the views for the game. These are various classes that defines GUI elements such as the pits and undo / end turn buttons. The action listeners for the buttons are included in the Board class, thus making the Board class a view as well as a Controller. The action listeners call the appropriate methods, and then update the model. The Board class also displays the game over notification screen when one side of the board is empty. See "Uses Cases.pdf".

## Instructions:
Compile project and run ```MancalaTest.java```.

![Start](https://i.imgur.com/x4G4AY2.png)

![Board](https://i.imgur.com/i9RManw.png)

## References:
* https://en.wikipedia.org/wiki/Mancala

## License
```
MIT License

Copyright (c) 2017 Rohan Kumar

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
