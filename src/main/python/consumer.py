from zeep import client

fileXchange = client("src/main/resources/FileXchangeService.wsdl").service

list = fileXchange.browse("")
for fileName in list:
    print(fileName) 

list = fileXchange.browse("ServerShare/")
for fileName in list:
    print(fileName) 

list = fileXchange.browse("ServerShare/Folder 2/")
for fileName in list:
    print(fileName) 

list = fileXchange.browse("ServerShare/Folder 2/Visual-Studio-Code-will-know-in-which-programming-language-you.jpg")
for fileName in list:
    print(fileName) 

content = download("ServerShare\Folder 2\Visual-Studio-Code-will-know-in-which-programming-language-you.jpg")
f = open("ClientShare/downloads/Visual-Studio-Code-will-know-in-which-programming-language-you.jpg", "wb")
f.write(content)
f.close()

