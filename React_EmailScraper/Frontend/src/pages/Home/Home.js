import { Container, Button, Typography, Box, Grid } from '@mui/material';
import FooterSmall from './footer';
import Analiz from './po5.jpeg';
import scrape from  './po3.jpeg';
import help from  './po4.jpeg';
import scrape2 from  './po1.jpeg';
import Analiz2 from  './po2.jpeg';


const Home = () => {
    return (
        <>
            <Grid container style={{ minHeight: '100vh', backgroundColor: '#e6f2f8', position: 'relative' }}>
                <Grid item xs={12}>
                <div style={{ height: '5vh' }} />
                    <Box sx={{ padding: 4 }}>
                        <Typography variant="h2" align="center" gutterBottom>
                            Bine ați venit la MailHarvest App
                        </Typography>
                        <Typography variant="h6" align="center" gutterBottom>
                            Introduceți Emailul și Parola pentru a începe
                        </Typography>
                    </Box>
                </Grid>
                
                <Grid item xs={12}>
                    <Container maxWidth="sm">
                        <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
                            <Button 
                                variant="contained" 
                                color="primary" 
                                fullWidth 
                                href="/login"
                            >
                                Start
                            </Button>
                        </Box>
                    </Container>
                </Grid>

                <Grid item xs={12} style={{ marginTop: '4rem' }}>
                    <Container>
                        <Box sx={{ display: 'flex', justifyContent: 'space-around', alignItems: 'center', flexWrap: 'wrap' }}>
                            <Box
                                sx={{
                                    width: '45%',
                                    height: '300px',
                                    backgroundImage: `url(${scrape})`,
                                    backgroundSize: 'cover',
                                    backgroundPosition: 'center',
                                    marginBottom: '2rem',
                                    backgroundColor: '#cccccc',
                                    border: '1px solid black'
                                }}
                            >
                                {/* Scrape ^^  */}
                            </Box>
                            <Box
                                sx={{
                                    width: '45%',
                                    height: '300px',
                                    backgroundImage: `url(${Analiz})`,
                                    backgroundSize: 'cover',
                                    backgroundPosition: 'center',
                                    marginBottom: '2rem',
                                    backgroundColor: '#cccccc',
                                    border: '1px solid black'
                                }}
                            >
                                {/* Analiz ^^  */}
                            </Box>
                        </Box>
                    </Container>
                </Grid>
                
                <Grid item xs={12}>
                    <Container>
                        <Box sx={{ textAlign: 'center', padding: '1rem' }}>
                            <Typography variant="body1" gutterBottom>
                                Această aplicație de email scraping vă ajută să extrageți tot ce este legat de mail-urile primite pe Gmail. Apăsați butonul Start sau Log In pentru a începe procesul și explorați diverse funcționalități oferite de aplicație.
                            </Typography>
                        </Box>
                    </Container>
                </Grid>

                <Grid item xs={12} style={{ marginTop: '4rem' }}>
                    <Container>
                        <Typography variant="h4" align="center" gutterBottom>
                            Cum funcționează aplicația
                        </Typography>
                        <Box sx={{ display: 'flex', justifyContent: 'space-around', alignItems: 'center', flexWrap: 'wrap' }}>
                            <Box
                                sx={{
                                    width: '45%',
                                    height: '300px',
                                    backgroundImage: `url(${help})`,
                                    backgroundSize: 'cover',
                                    backgroundPosition: 'center',
                                    marginBottom: '2rem',
                                    backgroundColor: '#cccccc',
                                    border: '1px solid black'
                                }}
                            >
                                {/* HELP ^^ */}
                            </Box>
                            <Box sx={{ width: '45%', height: '200px', marginBottom: '2rem' }}>
                                <Typography variant="body1">
                                    1. Introduceți adresa de email și Parola pentru înregistrare. Ce este această parolă de aplicație (App Password)? Este un cod generat de Gmail pentru accesul la contul dvs. din aplicații sau dispozitive mai puțin securizate. Pentru a primi acest cod, consultați <a href="/help">Help</a>.
                                </Typography>
                            </Box>
                            <Box sx={{ width: '45%', height: '200px', marginBottom: '2rem' }}>
                                <Typography variant="body1">
                                    2. Utilizați meniul din dreapta pentru a selecta emailurile de descărcat și pentru a aplica filtre. Acestea vor fi afișate într-un tabel după câteva secunde, unde puteți vizualiza, șterge și sorta emailurile, precum și descărca rezultatele filtrate în format CSV pe computerul dvs.
                                </Typography>
                            </Box>
                            <Box
                                sx={{
                                    width: '45%',
                                    height: '300px',
                                    backgroundImage: `url(${scrape2})`,
                                    backgroundSize: 'cover',
                                    backgroundPosition: 'center',
                                    marginBottom: '2rem',
                                    backgroundColor: '#cccccc',
                                    border: '1px solid black'
                                }}
                            >
                                {/* 2Scrape ^^ */}
                            </Box>
                            <Box
                                sx={{
                                    width: '45%',
                                    height: '300px',
                                    backgroundImage: `url(${Analiz2})`,
                                    backgroundSize: 'cover',
                                    backgroundPosition: 'center',
                                    marginBottom: '2rem',
                                    backgroundColor: '#cccccc',
                                    border: '1px solid black'
                                }}
                            >
                                {/* HELP ^^ */}
                            </Box>
                            <Box sx={{ width: '45%', height: '200px', marginBottom: '2rem' }}>
                                <Typography variant="body1">
                                    3. Pe lângă acestea, există și alte funcționalități, cum ar fi programarea unui scrape pentru a descărca emailurile primite zilnic la o anumită oră, vizualizarea statisticilor pe criterii și descărcarea acestora în format PDF, precum și trimiterea automată pe email a acestui PDF.
                                </Typography>
                            </Box>
                        </Box>
                    </Container>

                    <div style={{ height: '10vh' }} />
                    <Box sx={{ width: '50%', padding: '2rem', marginBottom: '2rem', margin: 'auto' }}>
                         <Typography variant="body1">
                            MailHarvest App este soluția ideală pentru gestionarea eficientă a corespondenței pe Gmail, oferind funcționalități intuitive de descărcare și filtrare automată a emailurilor, programare a rapoartelor și analize avansate, totul într-un mediu sigur și confidențial. Cu ajutorul nostru, vă puteți organiza corespondența într-un mod simplu și eficient, economisind timp și energie pentru alte sarcini importante.
                        </Typography>
                    </Box>
                    <div style={{ height: '10vh' }} />
                </Grid>

                <div style={{ height: '10vh' }} />
                
                <FooterSmall />
            </Grid>
        </>
    );
}

export default Home;