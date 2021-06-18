import React, { useState } from "react"
import { Redirect } from "react-router"
import _ from 'lodash'

import FilmCheckboxes from "./FilmCheckboxes"
import ErrorList from "./ErrorList"

const NewActorForm = (props) => {
  const [formPayload, setFormPayload] = useState({
    name: "",
    filmIds: ""
  })
  const [errors, setErrors] = useState({})
  const [actorId, setActorId] = useState(null)
  const [shouldRedirect, setShouldRedirect] = useState(false)

  const validForSubmission = () => {
    const errors = {}
    const requiredFields = ["name"]
    requiredFields.forEach(field => {
      if(formPayload[field].trim() === "") {
        errors[field] = "is blank"
      }
    })
    setErrors(errors)
    return _.isEmpty(errors)
  }

  const addActor = async() => {
    try {
      const response = await fetch(`/api/v1/actors`, {
        method: 'POST',
        headers: new Headers({
          'Content-Type': 'application/json'
        }),
        body: JSON.stringify(formPayload)
      })
      if (!response.ok) {
        if(response.status === 422) {
          const body = await response.json()
          return setErrors(body.errors)
        } else {
          const errorMessage = `${response.status} (${response.statusText})`
          const error = new Error(errorMessage)
          throw(error)
        }
      }

      const body = await response.json()
      setActorId(body.id)
      setShouldRedirect(true) 
    } catch(err) {
      console.error(`Error in fetch: ${err.message}`)
    }
  }

  const handleSubmit = (event) => {
    event.preventDefault()
    console.log(formPayload);
    if(validForSubmission()) {
      addActor()
    }
  }

  const handleInputChange = event => {
    setFormPayload({
      ...formPayload,
      [event.currentTarget.name]: event.currentTarget.value
    })
  }

  if (shouldRedirect) {
    return <Redirect push to={`/actors/${actorId}`} />
  }

  console.log(formPayload);

  return (
    <form className="callout" onSubmit={handleSubmit}>
      <ErrorList errors={errors} />
      <div>
        <label htmlFor="name">Name: </label>
        <input
          name="name"
          id="name"
          type="text"
          value={formPayload.name}
          onChange={handleInputChange}
        />
      </div>
      
      <FilmCheckboxes
        handleInputChange={handleInputChange}
        studioId={formPayload.studioId}
      />
      <input className="button" type="submit" value="Submit" />
    </form>
  )
}


export default NewActorForm
