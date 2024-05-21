import React, { useState } from 'react';
import PropTypes from 'prop-types';
import { alpha } from '@mui/material/styles';
import { TableHead } from '@mui/material';
import { TableRow } from '@mui/material';
import { TableCell } from '@mui/material';
import { TableSortLabel } from '@mui/material';
import { Toolbar } from '@mui/material';
import { Typography } from '@mui/material';
import { Tooltip } from '@mui/material';
import { Paper } from '@mui/material';
import { Checkbox } from '@mui/material';
import { IconButton } from '@mui/material';
import { TableContainer } from '@mui/material';
import { Table } from '@mui/material';
import { TableBody } from '@mui/material';
import { Box } from '@mui/material';
import { TablePagination } from '@mui/material';
import DeleteIcon from '@mui/icons-material/Delete';
import { visuallyHidden } from '@mui/utils';
import VisibilityIcon from '@mui/icons-material/Visibility';
import Button from '@mui/material/Button';
import { createPortal } from 'react-dom';
import AppContext from './Appcontext';

import { backendUrl } from '../../config/Config';
import axios, { all } from 'axios';

function descendingComparator(a, b, orderBy) {
  if (b[orderBy] < a[orderBy]) {
    return -1;
  }
  if (b[orderBy] > a[orderBy]) {
    return 1;
  }
  return 0;
}

function getComparator(order, orderBy) {
  return order === 'desc'
    ? (a, b) => descendingComparator(a, b, orderBy)
    : (a, b) => -descendingComparator(a, b, orderBy);
}

function stableSort(array, comparator) {
  if (!Array.isArray(array) || array.length === 0) {
    console.error('Array is undefined or empty.');
    return [];
  }

  const stabilizedThis = array.map((el, index) => [el, index]);
  stabilizedThis.sort((a, b) => {
    const order = comparator(a[0], b[0]);
    if (order !== 0) {
      return order;
    }
    return a[1] - b[1];
  });
  return stabilizedThis.map((el) => el[0]);
}

const headCells = [
  {
    id: 'Sender',
    numeric: false,
    disablePadding: true,
    label: 'Sender',
  },
  {
    id: 'Data',
    numeric: true,
    disablePadding: false,
    label: 'Data',
  },
  {
    id: 'Subject',
    numeric: true,
    disablePadding: false,
    label: 'Subject',
  },
  {
    id: 'Body',
    numeric: true,
    disablePadding: false,
    label: 'Body',
  },
];

function EnhancedTableHead(props) {
  const { onSelectAllClick, order, orderBy, numSelected, rowCount, onRequestSort } =
    props;
  const createSortHandler = (property) => (event) => {
    onRequestSort(event, property);
  };

  return (
    <TableHead>
      <TableRow>
        <TableCell padding="checkbox">
          <Checkbox
            color="primary"
            indeterminate={numSelected > 0 && numSelected < rowCount}
            checked={rowCount > 0 && numSelected === rowCount}
            onChange={onSelectAllClick}
            inputProps={{
              'aria-label': 'select all desserts',
            }}
          />
        </TableCell>
        {headCells.map((headCell) => (
          <TableCell
            key={headCell.id}
            align={headCell.numeric ? 'right' : 'left'}
            padding={headCell.disablePadding ? 'none' : 'normal'}
            sortDirection={orderBy === headCell.id ? order : false}
          >
            <TableSortLabel
              active={orderBy === headCell.id}
              direction={orderBy === headCell.id ? order : 'asc'}
              onClick={createSortHandler(headCell.id)}
            >
              {headCell.label}
              {orderBy === headCell.id ? (
                <Box component="span" sx={visuallyHidden}>
                  {order === 'desc' ? 'sorted descending' : 'sorted ascending'}
                </Box>
              ) : null}
            </TableSortLabel>
          </TableCell>
        ))}
      </TableRow>
    </TableHead>
  );
}

EnhancedTableHead.propTypes = {
  numSelected: PropTypes.number.isRequired,
  onRequestSort: PropTypes.func.isRequired,
  onSelectAllClick: PropTypes.func.isRequired,
  order: PropTypes.oneOf(['asc', 'desc']).isRequired,
  orderBy: PropTypes.string.isRequired,
  rowCount: PropTypes.number.isRequired,
};

function EnhancedTableToolbar(props) {
  const { numSelected, onShowFormClick, onDeleteClick } = props;

  return (
    <Toolbar
      sx={{
        pl: { sm: 2 },
        pr: { xs: 1, sm: 1 },
        ...(numSelected > 0 && {
          bgcolor: (theme) =>
            alpha(theme.palette.primary.main, theme.palette.action.activatedOpacity),
        }),
      }}
    >
      {numSelected > 0 ? (
        <Typography
          sx={{ flex: '1 1 100%' }}
          color="inherit"
          variant="subtitle1"
          component="div"
        >
          {numSelected} selected
        </Typography>
      ) : (
        <Typography
          sx={{ flex: '1 1 100%' }}
          variant="h6"
          id="tableTitle"
          component="div"
        >
          Emails
        </Typography>
      )}

      {numSelected === 1 ? (
        <React.Fragment>
          <Tooltip title="Show">
            <IconButton onClick={onShowFormClick}>
              <VisibilityIcon />
            </IconButton>
          </Tooltip>
          <Tooltip title="Delete">
            <IconButton onClick={onDeleteClick}>
              <DeleteIcon />
            </IconButton>
          </Tooltip>
        </React.Fragment>
      ) : (
        numSelected > 0 ? (
          <Tooltip title="Delete">
            <IconButton onClick={onDeleteClick}>
              <DeleteIcon />
            </IconButton>
          </Tooltip>
        ) : (
          <Tooltip title="Filter list">
            {/* <IconButton>
            </IconButton> */}
          </Tooltip>
        )
      )}
    </Toolbar>
  );
}

EnhancedTableToolbar.propTypes = {
  numSelected: PropTypes.number.isRequired,
  onShowFormClick: PropTypes.func.isRequired,
  onDeleteClick: PropTypes.func.isRequired,
};

function EmailForm(props) {
  const { onClose, emailData } = props;

  return (
    createPortal(
      <div style={{ position: 'fixed', top: '50%', left: '50%', transform: 'translate(-50%, -50%)', backgroundColor: 'white', padding: '20px', borderRadius: '8px', boxShadow: '0 0 10px rgba(0, 0, 0, 0.1)', zIndex: 1000, opacity: 1, width: '900px', maxHeight: '500px', overflowY: 'auto', display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center' }}>
        <Typography variant="h6" gutterBottom style={{ margin: '20px 0' }}>
          Detalii Email
        </Typography>
        <div style={{ maxHeight: '300px', overflowY: 'auto', width: '100%', marginBottom: '20px' }}>
          <Typography variant="subtitle1" gutterBottom>
            Sender: {emailData.Sender}
          </Typography>
          <Typography variant="subtitle1" gutterBottom>
            Data: {emailData.Date}
          </Typography>
          <Typography variant="subtitle1" gutterBottom>
            Subject: {emailData.Subject}
          </Typography>
          <Typography variant="subtitle1" gutterBottom>
            Body: {emailData.Body}
          </Typography>
        </div>
        <Button variant="contained" color="primary" onClick={onClose}>
          Close
        </Button>
      </div>,
      document.body
    )
  );
}


EmailForm.propTypes = {
  onClose: PropTypes.func.isRequired,
  emailData: PropTypes.object.isRequired,
};

export default function EnhancedTable(props) {
  const { rows: emailData, updateEmailData } = props;
  const [order, setOrder] = useState('asc');
  const [orderBy, setOrderBy] = useState('calories');
  const [selected, setSelected] = useState([]);
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(15);
  const [showForm, setShowForm] = useState(false);
  const [selectedEmail, setSelectedEmail] = useState(null);
  


  AppContext.fulldata = emailData;
  
  console.log("Din tabel fulldata",AppContext.fulldata);


  const handleRequestSort = (event, property) => {
    const isAsc = orderBy === property && order === 'asc';
    setOrder(isAsc ? 'desc' : 'asc');
    setOrderBy(property);
  };

  const handleSelectAllClick = (event) => {
    if (event.target.checked) {
      const newSelected = emailData.map((n) => n.id);
      setSelected(newSelected);
      return;
    }
    setSelected([]);
  };

  const handleDeleteClick = async () => {
    const updatedEmailData = emailData.filter((row) => !selected.includes(row.id));
    props.updateEmailData(updatedEmailData);

    setSelected([]);

    try {
        const token = localStorage.getItem('token');
        await axios.post(`${backendUrl}/api/delete`, {
            deletedRows: selected  
        }, {
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            }
        });
        console.log('Deleted items sent to the server successfully.');
    } catch (error) {
        console.error('Error sending deleted items to the server:', error.response || error.message);
    }
};




  const handleClick = (event, id) => {
    const selectedIndex = selected.indexOf(id);
    let newSelected = [];

    if (selectedIndex === -1) {
      newSelected = newSelected.concat(selected, id);
    } else if (selectedIndex === 0) {
      newSelected = newSelected.concat(selected.slice(1));
    } else if (selectedIndex === selected.length - 1) {
      newSelected = newSelected.concat(selected.slice(0, -1));
    } else if (selectedIndex > 0) {
      newSelected = newSelected.concat(
        selected.slice(0, selectedIndex),
        selected.slice(selectedIndex + 1),
      );
    }
    setSelected(newSelected);
  };

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    console.log("Selected value:", event.target.value);
    if (event.target.value === "All") {
        console.log("Setting rowsPerPage to length of emailData");
        if (Array.isArray(emailData)) {
            setRowsPerPage(emailData.length);
        }
    } else {
        console.log("Setting rowsPerPage to selected value");
        setRowsPerPage(parseInt(event.target.value, 10));
    }
    setPage(0);
};

  const isSelected = (id) => selected.indexOf(id) !== -1;

  const handleShowFormClick = () => {
    if (selected.length === 1) {
      const selectedEmailData = emailData.find((row) => row.id === selected[0]);
      setShowForm(true);
      setSelectedEmail({ ...selectedEmailData, Body: selectedEmailData.Body.slice(0, 50000) });
    }
  };

  const handleCloseForm = () => {
    setShowForm(false);
    setSelected([]);
    setSelectedEmail(null);
  };

  const emptyRows =
    page > 0 ? Math.max(0, (1 + page) * rowsPerPage - emailData.length) : 0;
  const visibleRows = React.useMemo(
    () => {
      if (!emailData || !Array.isArray(emailData) || emailData.length === 0) {
        return [];
      }

      return stableSort(emailData, getComparator(order, orderBy)).slice(
        page * rowsPerPage,
        page * rowsPerPage + rowsPerPage,
      );
    },
    [order, orderBy, page, rowsPerPage, emailData]
  );

  return (
<Box sx={{ width: '100%', maxWidth: 'calc(120vh)', overflowY: 'auto' }}>
  <Paper sx={{ width: '100%', mb: 2 }}>
    <EnhancedTableToolbar
      numSelected={selected.length}
      onShowFormClick={handleShowFormClick}
      onDeleteClick={handleDeleteClick}
    />
    <TableContainer sx={{ overflowY: 'auto', minHeight: '531px', minWidth: '800px' }}>
      <Table sx={{ minWidth: '100w' }} aria-labelledby="tableTitle" size='small'>
        <EnhancedTableHead
          numSelected={selected.length}
          order={order}
          orderBy={orderBy}
          onSelectAllClick={handleSelectAllClick}
          onRequestSort={handleRequestSort}
          rowCount={emailData && Array.isArray(emailData) ? emailData.length : 0}
        />
        <TableBody>
          {visibleRows.map((row, index) => {
            const isItemSelected = isSelected(row.id);
            const labelId = `enhanced-table-checkbox-${index}`;

            return (
              <TableRow
                hover
                onClick={(event) => handleClick(event, row.id)}
                role="checkbox"
                aria-checked={isItemSelected}
                tabIndex={-1}
                key={row.id}
                selected={isItemSelected}
                sx={{ cursor: 'pointer' }}
              >
                <TableCell padding="checkbox">
                  <Checkbox
                    color="primary"
                    checked={isItemSelected}
                    inputProps={{
                      'aria-labelledby': labelId,
                    }}
                  />
                </TableCell>
                <TableCell
                  component="th"
                  id={labelId}
                  scope="row"
                  padding="none"
                  align="left"
                  style={{ maxWidth: '150px', overflow: 'hidden', textOverflow: 'ellipsis', whiteSpace: 'nowrap' }}
                >
                  {row.Sender}
                </TableCell>
                <TableCell align="right" style={{ maxWidth: '110px', overflow: 'hidden', textOverflow: 'ellipsis', whiteSpace: 'nowrap' }}>
                  {row.Date.slice(0, 25)}...
                </TableCell>
                <TableCell align="right" style={{ maxWidth: '0px', overflow: 'hidden', textOverflow: 'ellipsis', whiteSpace: 'nowrap' }}>
                  {row.Subject.slice(0, 25)}...
                </TableCell>
                <TableCell align="right" style={{ maxWidth: '200px', overflow: 'hidden', textOverflow: 'ellipsis', whiteSpace: 'nowrap' }}>
                  {row.Body.slice(0, 25)}...
                </TableCell>
              </TableRow>
            );
          })}
          {emptyRows > 0 && (
            <TableRow
              style={{
                height: (33) * emptyRows,
              }}
            >
              <TableCell colSpan={6} />
            </TableRow>
          )}
        </TableBody>
      </Table>
    </TableContainer>
    <TablePagination
      rowsPerPageOptions={[15]}
      component="div"
      count={emailData && Array.isArray(emailData) ? emailData.length : 0}
      rowsPerPage={rowsPerPage}
      page={page}
      onPageChange={handleChangePage}
      onRowsPerPageChange={handleChangeRowsPerPage}
      labelRowsPerPage="Rows per page"
      SelectProps={{
        renderValue: (value) => {
          if (value === emailData.length) return 'All';
          return value;
        }
      }}
    />
  </Paper>
  {showForm && (
    <EmailForm emailData={selectedEmail} onClose={handleCloseForm} />
  )}
</Box>


  );
}

EnhancedTable.propTypes = {
  rows: PropTypes.array.isRequired,
};