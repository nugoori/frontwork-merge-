import { AppBar, Box, Button, IconButton, InputBase, Toolbar, Typography, alpha, styled } from "@mui/material";
import MenuIcon from '@mui/icons-material/Menu';
import SearchIcon from '@mui/icons-material/Search';
import { useNavigate } from "react-router-dom";

const navigator = useNavigate();

export default function NavigationBar() {
    return (
        <>
            <Box sx={{ flexGrow: 1 }}>
                <AppBar position="static" sx={{backgroundColor: "#353535"}}>
                    <Toolbar>
                        <IconButton
                            size="large"
                            edge="start"
                            color="inherit"
                            aria-label="open drawer"
                            sx={{ mr: 2 }}
                        >
                            {/* <MenuIcon /> 앱 로고 들어갈 것 */} 
                        </IconButton>
                        <Typography
                            variant="h6"
                            noWrap
                            component="div"
                            sx={{ flexGrow: 1, display: { xs: 'none', sm: 'block' } }}
                        >
                            로고 제목
                        </Typography>
                        <Button 
                            color="inherit" 
                            sx={{ pl: "15px", pr: "15px" }}
                            onClick={() => navigator('/auth')}
                        >
                            로그인
                        </Button>
                    </Toolbar>
                </AppBar>
            </Box>
        </>
    );
}