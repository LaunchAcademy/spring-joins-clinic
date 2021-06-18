import React from 'react'

const StudioTile = (props) => {
  const { id, name } = props.studio

  return (
    <div>
      <a href={`/studios/${id}`}>
        <h1>{name}</h1>
      </a>
    </div>
  )
}

export default StudioTile