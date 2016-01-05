//Enter into console @ https://github.com/react-bootstrap/react-bootstrap/tree/master/src

var all_links = document.getElementsByClassName("js-directory-link");

var link_el;
var react_comp_list = [];
for (var i = 0; i < all_links.length; i++) {
    link_el = all_links[i];
    var text_link = link_el.textContent;
    if (text_link.indexOf(".js") > -1 && text_link[0] == text_link[0].toUpperCase()) {
        react_comp_list.push(text_link.slice(0, -3));
    }
}

console.log(react_comp_list)

var clojure_script_code = "";
for (var i = 0; i < react_comp_list.length; i++) {
    var comp = react_comp_list[i];
    // (def Accordion (js/React.createFactory js/ReactBootstrap.Accordion))
    clojure_script_code +=
    "(def " + comp + " " + "(js/React.createFactory "
    + "js/ReactBootstrap." + comp + "))\n"
}

console.log(clojure_script_code)