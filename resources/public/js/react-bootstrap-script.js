//Enter into console @ https://github.com/react-bootstrap/react-bootstrap/tree/master/src

log = function(msg) { console.log(msg) };

var all_links = document.getElementsByClassName("js-directory-link");
console.log("nr. of js-directory-links: " + all_links.length);

var link_el;
for (link_el in all_links) {
    var text_link = link_el.textContent;
    if (".js" in text_link) {
        console.log(text_link);
    }
}