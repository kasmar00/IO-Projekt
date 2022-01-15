// lista transformacji
const transformationsList = document.querySelector("#transformations");
// przycisk submit
const submitButton = document.querySelector("#submit");
// message to be transformed
const message = document.querySelector("#message");

// payload do api
const payload = {
  text: "",
  transformations: [],
};

// wynik
const result = document.getElementById("transformed");

message.addEventListener("change", (event) => {
  payload.text = event.target.value;
});

submitButton.addEventListener("click", () => {
  fetch("/api/transform", {
    method: "POST",
    body: JSON.stringify(payload),
    headers: {
      "Content-Type": "application/json",
    },
  })
    .then((response) => response.json())
    .then((data) => {
      result.value = data.text;
    });
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
      payload.transformations.push(operation);
    } else {
      const index = payload.transformations.indexOf(operation);
      if (index > -1) {
        payload.transformations.splice(index, 1);
      }
    }
  });
  transformationsList.appendChild(div);
};

const fetchAvailableTransforms = () => {
  fetch("api/transform", {
    method: "GET",
  })
    .then((response) => response.json())
    .then((data) => {
      data.forEach((v) => addOptionToList(v));
    });
};

fetchAvailableTransforms();
