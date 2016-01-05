import urllib.request

index_file_url = "https://raw.githubusercontent.com/react-bootstrap/react-bootstrap/master/src/index.js"
response = urllib.request.urlopen(index_file_url)
data = response.read()
code = data.decode('utf-8')

# print(code)
components = []
for line in code.split("\n"):
    if "export" in line:
        line_comp = line.split(" ")
        components.append(line_comp[1])

components = list(filter(lambda s: s[0].isupper(), components))

cljs_code = ""
for comp in components:
    cljs_code += "(def " + comp + " " + "(js/React.createFactory " + "js/ReactBootstrap." + comp + "))\n"

print(cljs_code)
