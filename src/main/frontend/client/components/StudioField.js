import React, { useEffect, useState } from 'react'

const StudioField = props => {
  const [studios, setStudios] = useState([])

  const fetchStudios = async () => {
    try {
      const response = await fetch("/api/v1/studios")
      if(!response.ok) {
        const errorMessage = `${response.status} (${response.statusText})`
        const error = new Error(errorMessage)
        throw(error)
      }
      const studiosData = await response.json()
      studiosData.unshift({name: "", id: null})
      setStudios(studiosData)
    } catch(err) {
      console.error(`Error in fetch: ${err.message}`)
    }
  }

  useEffect(() => {
    fetchStudios()
  }, [])

  const studioOptions = studios.map(studio => {
    return(
      <option key={studio.id} value={studio.id}>
        {studio.name}
      </option>
    ) 
  })

  return(
    <div>
      <label htmlFor="studioId">Studio:</label>
      <select name="studioId" id="studioId" onChange={props.handleInputChange} value={props.studioId}>
        {studioOptions}
      </select>
    </div>
  )
}

export default StudioField