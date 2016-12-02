/**
    * @author Bazlur Rahman Rokon
    * @since 12/2/16.
    */

define(function () {

    return {
        read: function (str) {

        },
        write: function(obj /*, opts */) {
            // If this is an Array, extract the self URI and then join using a newline
            if (obj instanceof Array) {
                return obj.map(function(resource) {
                    return resource._links.self.href;
                }).join('\n');
            } else { // otherwise, just return the self URI
                return obj._links.self.href;
            }
        }
    }
});