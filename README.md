
	function generateRandomString(length) {
	    var result = '';
	    var characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
	    var charactersLength = characters.length;
	    for (var i = 0; i < length; i++) {
	        result += characters.charAt(Math.floor(Math.random() * charactersLength));
	    }
	    return result;
	}
	var randomString = generateRandomString(20); 
	pm.globals.set("requestId", randomString);
	
	pm.globals.set("timestamp", new Date().getTime());

Above is the script to add in pre request script of postman


Postman body exmaple for any request
{
    "token": "{{requestId}}",
	"data": {
        
    },
	"reqid": "{{requestId}}",
	"_client_ts": "{{timestamp}}",
	"_client_type" : "web"
}
