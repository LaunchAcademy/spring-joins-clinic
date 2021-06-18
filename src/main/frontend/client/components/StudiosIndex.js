import React, { useEffect, useState } from 'react';

import StudioTile from "./StudioTile"

const StudiosIndex = props => {
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
      setStudios(studiosData)
    } catch(err) {
      console.error(`Error in fetch: ${err.message}`)
    }
  }

  useEffect(() => {
    fetchStudios()
  }, [])

  const studioTiles = studios.map(studio => {
    return (
      <StudioTile
        key={studio.id}
        studio={studio}
      />
    )
  })

  return(
    <div>
      {studioTiles}
    </div>
  )
}

export default StudiosIndex