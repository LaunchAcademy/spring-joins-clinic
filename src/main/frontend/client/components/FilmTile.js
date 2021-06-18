import React from 'react'

const FilmTile = (props) => {
  const { id, name } = props.film

  return (
    <div>
      <a href={`/films/${id}`}>
        <h1>{name}</h1>
      </a>
    </div>
  )
}

export default FilmTile