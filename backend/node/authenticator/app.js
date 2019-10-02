const express = require("express");
const app = express();
const port = 3001;

app.get("/", function(req, res, next) {
  res.send("Hello World!");
});

app.listen(port, function() {
  console.log(`App is listening on port ${port}`);
});
