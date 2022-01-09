params = {};

// lista transformacji
const transformationsList = document.querySelector("#transformations");
// przycisk submit
const submitButton = document.querySelector("#submit");
// message to be transformed
const message = document.querySelector("#message");

// lista aktywnych operacji
const operations = [];

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

const addOptionToList = (operation) => {
  const div = document.createElement("div");
  div.className = "form-check";
  div.innerHTML = `
    <input class="form-check-input" type="checkbox" value="" id="${operation}_checkbox">
    <label class="form-check-label" for="${operation}_checkbox">
        ${operation}
    </label>
  `;
  div.children[0].addEventListener("change", (e) => {
    console.log(
      `${e.target.id} is ${e.target.checked} operation is ${operation}`
    );
    if (e.target.checked) {
      operations.push(operation);
    } else {
      const index = operations.indexOf(operation);
      if (index > -1) {
        operations.splice(index, 1);
      }
    }
  });
  transformationsList.appendChild(div);
};

// this should be replaced by fetching from backend
[
  "noop",
  "inverse",
  "expand",
  "to shortcuts",
  "from shortcuts",
  "lower",
  "upper",
  "capitalize",
].forEach((v) => addOptionToList(v));
