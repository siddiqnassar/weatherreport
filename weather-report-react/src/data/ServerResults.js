import axios from "axios";

export async function getApiResults(cityName) {

  return await (
    await axios.get(
      "http://localhost:8080/api/get/city/forecast?cityname=" + cityName
    )
  ).data;
}
