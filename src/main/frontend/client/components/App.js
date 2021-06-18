import React, { useEffect } from "react"
import { hot } from "react-hot-loader/root"
import "foundation-sites"
import $ from "jquery"
import "../assets/scss/main.scss"

import { Route, BrowserRouter, Switch, Redirect } from "react-router-dom"

import StudiosIndex from "./StudiosIndex"
import StudioShow from "./StudioShow"
import NewFilmForm from "./NewFilmForm"
import NewActorForm from "./NewActorForm"
import FilmShow from "./FilmShow"

const App = props => {
  useEffect(() => {
    $(document).foundation()
  }, [])

  return(
    <BrowserRouter>
      <Switch>
        <Route exact path="/">
          <Redirect to="/studios" />
        </Route>
        <Route exact path="/studios" component={StudiosIndex} />
        <Route exact path="/studios/:id" component={StudioShow} />
        <Route exact path="/films/new" component={NewFilmForm} />
        <Route exact path="/actors/new" component={NewActorForm} />
        <Route exact path="/films/:id" component={FilmShow} />
      </Switch>
    </BrowserRouter>
  )
}

export default hot(App)
