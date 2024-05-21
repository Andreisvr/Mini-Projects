import * as React from 'react';
import Divider from '@mui/material/Divider';
import Paper from '@mui/material/Paper';
import MenuList from '@mui/material/MenuList';
import MenuItem from '@mui/material/MenuItem';
import ListItemText from '@mui/material/ListItemText';
import ListItemIcon from '@mui/material/ListItemIcon';
// import Typography from '@mui/material/Typography';
import ContentCut from '@mui/icons-material/ContentCut';
import ContentCopy from '@mui/icons-material/ContentCopy';
import ContentPaste from '@mui/icons-material/ContentPaste';
import CloudDownloadOutlinedIcon from '@mui/icons-material/CloudDownloadOutlined'; // Icon pentru descărcarea generică
import { GetAppOutlined as GetAppOutlinedIcon } from '@mui/icons-material'; // Icon pentru descărcarea generică
// import TextField from '@mui/material/TextField'; // Importăm TextField din Material-UI
import { TextField } from '@mui/material';
import { Typography } from '@mui/material';

export default function IconMenu() {
  return (
    <Paper sx={{ width: 320, maxWidth: '100%' }}>
      <MenuList>
        {/* Textfield pentru data de început */}
        <MenuItem>
          <ListItemIcon>
            <ContentCut fontSize="small" />
          </ListItemIcon>
          <ListItemText>
            <TextField
              label="Select start date" // Eticheta pentru câmpul de text
              type="date" // Specificăm tipul de input ca fiind de tip date
              InputLabelProps={{ shrink: true }} // Pentru a face eticheta să se micsoreze când se completează câmpul
            />
          </ListItemText>
          <Typography variant="body2" color="text.secondary">
            {/* {pot adauga text in dreapta} */}
          </Typography>
        </MenuItem>
        
        {/* Textfield pentru data de sfârșit */}
        <MenuItem>
          <ListItemIcon>
            <ContentCopy fontSize="small" />
          </ListItemIcon>
          <ListItemText>
            <TextField
              label="Select end date" // Eticheta pentru câmpul de text
              type="date" // Specificăm tipul de input ca fiind de tip date
              InputLabelProps={{ shrink: true }} // Pentru a face eticheta să se micsoreze când se completează câmpul
            />
          </ListItemText>
          <Typography variant="body2" color="text.secondary">
            {/* {pot adauga text in dreapta} */}
          </Typography>
        </MenuItem>
        
        <MenuItem>
          <ListItemIcon>
            <ContentPaste fontSize="small" />
          </ListItemIcon>
          <ListItemText>Paste</ListItemText>
          <Typography variant="body2" color="text.secondary">
            ⌘V
          </Typography>
        </MenuItem>
        <Divider />
        <MenuItem>
          <ListItemIcon>
            <CloudDownloadOutlinedIcon fontSize="small" />
          </ListItemIcon>
          <ListItemText>Download Selected CSV</ListItemText> {/* Text pentru descărcarea în format CSV */}
        </MenuItem>
        <MenuItem>
          <ListItemIcon>
            <GetAppOutlinedIcon fontSize="small" />
          </ListItemIcon>
          <ListItemText>Download Selected PDF</ListItemText> {/* Text pentru descărcarea în format PDF */}
        </MenuItem>
      </MenuList>
    </Paper>
  );
}
