#!/usr/bin/python
# -*- coding: utf-8 -*-

from Cheetah.Template import Template

mail = Template(file="mail.tmpl")
mail.title = u"Зима близко!"
mail.recipients=["Rob", "Bran", "John"]

print mail
