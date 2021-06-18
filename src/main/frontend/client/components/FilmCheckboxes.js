import React, { useEffect, useState } from 'react'

const FilmCheckboxes = props => {
  const [films, setFilms] = useState([])

  const fetchFilms = async () => {
    try {
      const response = await fetch("/api/v1/films")
      if(!response.ok) {
        const errorMessage = `${response.status} (${response.statusText})`
        const error = new Error(errorMessage)
        throw(error)
      }
      const filmsData = await response.json()
      setFilms(filmsData)
    } catch(err) {
      console.error(`Error in fetch: ${err.message}`)
    }
  }

  useEffect(() => {
    fetchFilms()
  }, [])

  const filmCheckboxes = films.map(film => {
    return (
      <div>
        <input type="checkbox" key={film.id} name="filmIds" value={film.id} />{film.name}<br/>      
      </div>
    ) 
  })

  return(
    <fieldset>
      <legend htmlFor="filmIds">Films:</legend>
      {filmCheckboxes}
    </fieldset>
  )
}

export default FilmCheckboxes