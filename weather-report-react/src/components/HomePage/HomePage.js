import * as React from "react";
import { makeStyles } from "@mui/styles";
import { Typography } from "@mui/material";
import { useEffect, useState } from "react";
import TextField from "@mui/material/TextField";
import FormControl from "@mui/material/FormControl";
import Button from "@mui/material/Button";
import CircularProgress from "@mui/material/CircularProgress";

import { getApiResults } from "../../data/ServerResults";
const useStyles = makeStyles({
  cityFormParentBlock: {
    paddingTop: "20px",
    paddingLeft: "20px",
  },
  futureTempertures: {
    width: "40%",
  },
  predictionWindow: {
    width: "40%",
  },
  foreCastBlock: {
    display: "flex",
  },
  recommendationTitle: {
    color: "brown",
    padding: "10px !important",
  },
  submitButton: {
      backgroundColor: '#1976d2 !important',
      color: '#fff !important',
      marginTop: '20px !important',
      marginBottom: '20px !important',
  },
});
function HomePage(props) {
  const classes = useStyles();
  const [foreCastResults, setForeCastResults] = useState(null);
  const [cityName, setCityName] = useState("Bangalore");
  const [cityError, setCityError] = useState(null);
  useEffect(() => {
  }, []);
  function changeCityInput(event) {
    setCityName(event.target.value);
  }
  async function validateAndfetchData() {
    setCityError(null);
    setForeCastResults("loading");
    if (
      cityName.trim() === undefined ||
      cityName.trim() === "" ||
      cityName.trim() === null
    ) {
      setForeCastResults(null);
      setCityError("Enter Valid Input");
    } else {
      setForeCastResults(null);
      await setForeCastResults(await getApiResults(cityName));
    }
    console.log("validateAndfetchData called");
  }
  return (
    <Typography variant="div" component="div">
      <Typography
        variant="div"
        component="div"
        className={classes.cityFormParentBlock}
      >
        <FormControl>
          <TextField
            required
            id="cityText"
            label="Enter City Name"
            error={cityError !== null}
            defaultValue="Bangalore"
            helperText="City Name is Required to see the forecast"
            onChange={changeCityInput}
          />
          <Button onClick={validateAndfetchData} className={classes.submitButton}>Submit</Button>
        </FormControl>
        {foreCastResults === "loading" ? (
          <CircularProgress />
        ) : foreCastResults !== null ? (
          <Typography
            component="div"
            variant="div"
            className={classes.foreCastBlock}
          >
            {/* <Typography component="p" variant="p">
                {" "}
                {foreCastResults["message"]}
              </Typography> */}
            {foreCastResults["predictionWindow"] === null ? null : (
              <Typography
                component="div"
                variant="div"
                className={classes.predictionWindow}
              >
                <Typography
                  component="p"
                  variant="p"
                  className={classes.recommendationTitle}
                >
                  Future Recommendations :-
                </Typography>
                {Object.keys(foreCastResults["predictionWindow"]).map((key) => (
                  <li>
                    {" "}
                    {key} : {foreCastResults["predictionWindow"][key]}
                  </li>
                ))}
              </Typography>
            )}

            {foreCastResults["futureDayTemperatures"] === null ? (
              "Error Message City Not Found : " + foreCastResults["message"]
            ) : (
              <Typography
                component="div"
                variant="div"
                className={classes.futureTempertures}
              >
                <Typography
                  component="p"
                  variant="p"
                  className={classes.recommendationTitle}
                >
                  Future Temperatures Per Date :-
                </Typography>
                {Object.keys(foreCastResults["futureDayTemperatures"]).map(
                  (key) => (
                    <li>
                      {" "}
                      {key} :{" "}
                      {foreCastResults["futureDayTemperatures"][key].join(", ")}
                    </li>
                  )
                )}
              </Typography>
            )}
          </Typography>
        ) : null}
      </Typography>
    </Typography>
  );
}

export default HomePage;
