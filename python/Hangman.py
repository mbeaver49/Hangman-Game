import os
import subprocess
from random import seed, randint
from tkinter import Tk, Canvas, Frame, BOTH


class Game():
    def __init__(self):
        super().__init__()

        self.Game()

    def Game(self):
        words = []
        with open("words.txt") as wordFile:
            for line in wordFile.readlines():
                words.append(line[:len(line)-1])

        gameOn = True
        while gameOn is True:
            guessWord = words[randint(0, len(words)-1)].replace(" ", "")
            displayChars = ['?'] * len(guessWord)
            rightAnswers = 0
            wrongAnswers = 0
            print(*displayChars, sep='')

            guessing = True
            while guessing is True:
                guess = str(input("Guess a letter: "))
                if guess == "exit":
                    exit()
                t = 0
                correctGuess = False
                for ch in guessWord:
                    print(ch)
                    try:
                        c = displayChars.index(guess)
                        correctGuess = False
                        break
                    except ValueError:
                        pass
                    if ch == guess:
                        correctGuess = True
                    t += 1
                if correctGuess is True:
                    print("Right Answer")
                    for i in range(len(displayChars)):
                        if guess == guessWord[i]:
                            displayChars[i] = guess
                            rightAnswers += 1
                    print(*displayChars, sep='')
                else:
                    wrongAnswers += 1
                    print("Wrong Answer")
                    print(*displayChars, sep='')

                if rightAnswers > len(guessWord)-1:
                    print("You Won!!!")
                    break
                if wrongAnswers == 5:
                    print("You Lost...")
                    break
            decision = str(input("Keep Playing? Y/N\n"))
            if decision == 'N':
                exit()


class DrawHM(Frame):

    def __init__(self):
        super().__init__()

        self.initUI()

    def initUI(self):

        self.master.title("Hangman")
        self.pack(fill=BOTH, expand=1)

        canvas = Canvas(self)
        canvas.create_line(15, 25, 200, 25)
        canvas.create_line(300, 35, 300, 200, dash=(4, 2))
        canvas.create_line(55, 85, 155, 85, 105, 180, 55, 85)

        canvas.pack(fill=BOTH, expand=1)


def main():

    root = Tk()
    ex = DrawHM()
    root.geometry("400x250+300+300")
    root.mainloop()
    g = Game()


if __name__ == '__main__':
    main()
