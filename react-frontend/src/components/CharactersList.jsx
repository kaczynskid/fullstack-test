import React from "react"
import {Paper, Table, TableBody, TableCell, TableFooter, TableHead, TablePagination, TableRow} from "@material-ui/core";

class CharactersListHeader extends React.Component {
  render() {
    return (
      <TableRow>
        <TableCell>Name</TableCell>
        <TableCell align="center">Gender</TableCell>
        <TableCell align="right">Birth Year</TableCell>
        <TableCell>Homeworld</TableCell>
      </TableRow>
    )
  }
}

class EmptyListRow extends React.Component {
  render() {
    return (
      <TableRow key="0">
        <TableCell rowSpan={4}>no data</TableCell>
      </TableRow>
    )
  }
}

class CharactersListRow extends React.Component {
  render() {
    return this.props.characters.map(character => (
      <TableRow key={character.id}>
        <TableCell>{character.name}</TableCell>
        <TableCell align="center">{character.gender}</TableCell>
        <TableCell align="right">{character.birthYear}</TableCell>
        <TableCell>{character.homeWorld.name}</TableCell>
      </TableRow>
    ))
  }
}

class CharactersList extends React.Component {

  state = {
    content: [],
    number: 0,
    size: 10,
    totalElements: 0
  };

  componentDidMount() {
    this.fetchPage(this.state.number, this.state.size)
  }

  handleChangePage = (event, newPage) => {
    this.fetchPage(newPage, this.state.size)
  };

  handleChangeRowsPerPage = (event) => {
    this.fetchPage(0, parseInt(event.target.value));
  };

  fetchPage = (number, size) => {
    fetch("/api/characters?page=" + number + "&size=" + size, {
      headers: {
        'Accept': 'application/vnd.star-wars.v1+json;charset=UTF-8'
      }
    })
      .then(res => res.json())
      .then(json => this.setState(state => json));
  };

  render() {
    return (
      <Paper>
        <div>
          <Table>
            <TableHead>
              <CharactersListHeader />
            </TableHead>
            <TableBody>
              {this.state.content ? <CharactersListRow characters={this.state.content} /> : <EmptyListRow />}
            </TableBody>
            <TableFooter>
              <TableRow>
                <TablePagination
                  rowsPerPageOptions={[10, 20, 30]}
                  colSpan={4}
                  count={this.state.totalElements}
                  rowsPerPage={this.state.size}
                  page={this.state.number}
                  onChangePage={this.handleChangePage}
                  onChangeRowsPerPage={this.handleChangeRowsPerPage}
                />
              </TableRow>
            </TableFooter>
          </Table>
        </div>
      </Paper>
    )
  }
}

export default CharactersList
