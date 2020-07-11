Restful请求里get和post的区别：
get的请求标准是幂等的，而post不是，即前者资源不会被修改，可认为请求安全，而后者则可能导致资源被修改。
get的请求数据只包括ascii码，但post还可以包括二进制数据。
get请求有长度限制，post没有。
get请求的数据都在URL中，参数通过URL传递，post数据不会被缓存。
get请求可被缓存，post则不可以。
get获取资源，post发布资源。