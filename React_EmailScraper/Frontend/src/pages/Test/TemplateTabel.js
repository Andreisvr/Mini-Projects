import { Table, TableBody, TableCell, TableContainer, TableHead, TableRow, Paper, Checkbox } from '@mui/material';
import { useState } from 'react';
const TemplateTabel = (scrapedData) =>{   //numele componentei sa fie acelasi cu al filei 
                                            //(ex. fila TemplateTable.js si componenta TemplateTable)
                // scrapedData este un parametru al componentei. 
                //Componenta functioneaza pe baza acestui parametru. In cazul nostru o sa fie o lista de obiecte json

                // Mock data. Cam asa o sa arate lista scrapeData ca parametru. O sa vina direct de la backend. 
                //Asumati ca asta vine de la backend si incercati si o adaptati la tableul vostru
                const scrapeDataExample = [
                    { id: 1, subject: 'Subject 1', from: 'Ana', to: 'Andrei', body: 'Email Body 1', date: '02.04.2024 16:32 PM' },
                    { id: 2, subject: 'Subject 2', from: 'Ioan', to: 'Andrei', body: 'Email Body 2', date: '02.04.2024 16:40 PM' },
                    { id: 3 ,subject: 'Subject 3', from: 'Alex', to: 'Andrei', body: 'Email Body 3', date: '02.04.2024 18:20 PM' },
                ];
                
                //State cu parametru lista. Lista va contine randurile selectate
                const [selected, setSelected] = useState([]);

                // Functie cu parametru id. Adauga sau scoate randuri selectate din lista de randuri selectate
                const handleToggleSelect = (id) => {
                    const selectedIndex = selected.indexOf(id);
                    let newSelected = [];
                
                    if (selectedIndex === -1) {
                      newSelected = newSelected.concat(selected, id);
                    } else {
                      newSelected = selected.filter((selectedId) => selectedId !== id);
                    }
                
                    setSelected(newSelected);
                  };

                // Ce se render-uieste
                return (
                    <TableContainer component={Paper}>
                      <Table sx={{ minWidth: 650 }} aria-label="simple table">
                        <TableHead>
                          <TableRow>
                            <TableCell padding="checkbox"></TableCell>
                            <TableCell>Subject</TableCell>
                            <TableCell>From</TableCell>
                            <TableCell>To</TableCell>
                            <TableCell>Body</TableCell>
                            <TableCell>Date</TableCell>
                          </TableRow>
                        </TableHead>
                        <TableBody>
                          {scrapeDataExample.map((row) => (  //Functia map ia o lista ca parametru si randeaza componenta ScrapeResultRow
                            <ScrapeResultRow                //Ce poate fi gasita mai jos cu parametrii fiecarui obiect din lista.
                              key={row.id}                  //Similara unui for
                              row={row}
                              isSelected={selected.includes(row.id)}
                              onToggleSelect={handleToggleSelect}
                            />
                          ))}
                        </TableBody>
                      </Table>
                    </TableContainer>
                  );
}

const ScrapeResultRow = ({ row, isSelected, onToggleSelect }) => {  //Parametrii unui rand de coloana. 
    return (
      <TableRow
        hover
        onClick={() => onToggleSelect(row.id)}
        role="checkbox"
        aria-checked={isSelected}
        selected={isSelected}
        sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
      >
        <TableCell padding="checkbox">
          <Checkbox
            color="primary"
            checked={isSelected}
            inputProps={{
              'aria-labelledby': `enhanced-table-checkbox-${row.id}`,
            }}
          />
        </TableCell>
        <TableCell component="th" scope="row">
          {row.subject}
        </TableCell>
        <TableCell>{row.from}</TableCell>
        <TableCell>{row.to}</TableCell>
        <TableCell>{row.body}</TableCell>
        <TableCell>{row.date}</TableCell>
      </TableRow>
    );
  };
export default TemplateTabel;