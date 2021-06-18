import React, { useEffect, useState } from 'react';

const FilmShow = (props) => {
  const [film, setFilm] = useState({ actors: [] })
  const filmId = props.match.params.id
  
  const fetchFilm = async () => {
    try {
      const response = await fetch(`/api/v1/films/${filmId}`)
      if(!response.ok) {
        const errorMessage = `${response.status} (${response.statusText})`
        const error = new Error(errorMessage)
        throw(error)
      }
      const filmData = await response.json()
      setFilm(filmData)
    } catch(err) {
      console.error(`Error in fetch: ${err.message}`)
      setFilm(null)
    }
  }

  useEffect(() => {
    fetchFilm()
  }, [])

  if(!film) {
    return(
      <h1>That Film could not be found!</h1>
    )
  } 

  const actorListItems = film.actors.map(actor => {
    return <li key={actor.id}>{actor.name}</li>
  })
  

  return (
    <div>
      <h1>{film.name}</h1>
      <ul>{actorListItems}</ul>
    </div>
  )
}

export default FilmShow