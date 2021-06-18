import React, { useEffect, useState } from 'react';
import { useLocation } from "react-router-dom"
import FilmTile from "./FilmTile"

const StudioShow = props => {
  let location = useLocation();
  const [studio, setStudio] = useState(
    { films: [] }
  )
  
  const fetchStudio = async () => {
    try {
      const studioId = props.match.params.id
      const response = await fetch(`/api/v1/studios/${studioId}`)
      if(!response.ok) {
        const errorMessage = `${response.status} (${response.statusText})`
        const error = new Error(errorMessage)
        throw(error)
      }
      const studioData = await response.json()
      setStudio(studioData)
    } catch(err) {
      console.error(`Error in fetch: ${err.message}`)
    }
  }

  useEffect(() => {
    fetchStudio()
  }, [location.pathname])

  const filmTiles = studio.films.map(film => {
    return (
      <FilmTile
        key={film.id}
        film={film}
      />
    )
  })

  return(
    <div>
      <h1>{studio.name}</h1>
      {filmTiles}
    </div>
  )
}

export default StudioShow