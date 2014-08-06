#!/usr/bin/python

import sqlite3

conn = sqlite3.connect('./library.db')
conn.row_factory = sqlite3.Row

c = conn.cursor()
c.execute('SELECT title, author FROM books WHERE year between ? AND ?', (2000, 2001))

books = c.fetchall()

conn.close()

print books 

for book in books:
    print type(book)
    print book['title'] + " was written by " + book['author']
