import { Box } from '@mui/material'
import BoardUpdateView from '../Board/BoradUpdateView'
import ProductUpdateView from '../Product/ProductUpdateView'

export default function UpdateView() {
  
  return (
    <Box>
        <BoardUpdateView />
        <ProductUpdateView />
    </Box>
  )
}
