params = {};

// lista transformacji
const transformationsList = document.querySelector("#transformations");
// przycisk submit
const submitButton = document.querySelector("#submit");
// message to be transformed
const message = document.querySelector("#message");

message.addEventListener(
  "change",
  () => {
    console.log(message.value);
  },
  false
);

submitButton.addEventListener("click", () => {
  console.log("aaaaaa");
});
