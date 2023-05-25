import { ChangeEvent, useRef, useEffect } from 'react'
import { useNavigate, useParams } from 'react-router-dom';
import { useCookies } from 'react-cookie';
import axios, { AxiosResponse } from 'axios';

import { Box, Fab, Input, Divider, Typography, IconButton } from '@mui/material'
import CreateIcon from '@mui/icons-material/Create';
import AddAPhotoIcon from '@mui/icons-material/AddAPhoto';

import { FILE_UPLOAD_URL, GET_BOARD_URL, PATCH_BOARD_URL, PATCH_PRODUCT_URL, authorizationHeader, mutipartHeader } from 'src/constants/api';
import { PatchBoardDto, PatchProductDto } from 'src/apis/request/board';
import { GetBoardResponseDto, PatchBoardResponseDto } from 'src/apis/response/board';
import ResponseDto from 'src/apis/response';
import { PatchProductResponseDto } from 'src/apis/response/product';
import { Product } from 'src/interfaces';
import { usePatchProductStore, useUserStore } from 'src/stores';

export default function BoardUpdateView() {
    // hook //
    const navigator = useNavigate();

    const imageRef = useRef<HTMLInputElement | null>(null);
    const imageRef2 = useRef<HTMLInputElement | null>(null);
    const imageRef3 = useRef<HTMLInputElement | null>(null);

    const [cookies] = useCookies();
    const { user } = useUserStore();
    const { boardNumber } = useParams();
    const { productNumber } = useParams();

    const { boardContent, boardImgUrl1, boardImgUrl2, boardImgUrl3, tag } = usePatchProductStore();
    const { product1, product2, product3, product4, product5, product6 } = usePatchProductStore();
    const { setBoardContent, setBoardImgUrl1, setBoardImgUrl2, setBoardImgUrl3, setTag } = usePatchProductStore();
    const { setProduct1, setProduct2, setProduct3, setProduct4, setProduct5, setProduct6 } = usePatchProductStore();

    const accessToken = cookies.accessToken;

    // event handler //
    const onBoardImageUploadButtonHandler = () => {
        if (!imageRef.current) return;
        imageRef.current.click();
    }
    const onBoardImageUploadButtonHandler2 = () => {
        if (!imageRef2.current) return;
        imageRef2.current.click();
    }
    const onBoardImageUploadButtonHandler3 = () => {
        if (!imageRef3.current) return;
        imageRef3.current.click();
    }

    const onBoardImageUploadChangeHandler = (event: ChangeEvent<HTMLInputElement>) => {
        if (!event.target.files) return;
        const data = new FormData();
        data.append('file', event.target.files[0]);
        axios.post(FILE_UPLOAD_URL, data, mutipartHeader())
            .then((response) => boardImageUploadResponseHandler(response))
            .catch((error) => boardImageUploadErrorHandler(error))
    }
    const onBoardImageUploadChangeHandler2 = (event: ChangeEvent<HTMLInputElement>) => {
        if (!event.target.files) return;
        const data = new FormData();
        data.append('file', event.target.files[0]);
        axios.post(FILE_UPLOAD_URL, data, mutipartHeader())
            .then((response2) => boardImageUploadResponseHandler2(response2))
            .catch((error) => boardImageUploadErrorHandler(error))
    }
    const onBoardImageUploadChangeHandler3 = (event: ChangeEvent<HTMLInputElement>) => {
        if (!event.target.files) return;
        const data = new FormData();
        data.append('file', event.target.files[0]);
        axios.post(FILE_UPLOAD_URL, data, mutipartHeader())
            .then((response3) => boardImageUploadResponseHandler3(response3))
            .catch((error) => boardImageUploadErrorHandler(error))
    }

    const onUpdateHandler = () => {
        patchBoard();
        
        if (!product1 || !product2 || !product3) {
            alert('상,하의 신발 정도는 올려주세요!');
            return;
        } 

        const productList: Product[] = [product1, product2, product3];
        if (product4) productList.push(product4);
        if (product5) productList.push(product5);
        if (product6) productList.push(product6);

        productList.forEach(product => patchProduct(product));
    }

    const getBoard = () => {
      axios.get(GET_BOARD_URL(Number(boardNumber as string)))
        .then((response) => getBoardResponseHandler(response))
        .catch((error) => getBoardErrorHandler(error))
    }
    
    const patchBoard = async () => {
        const data: PatchBoardDto = { boardNumber: Number(boardNumber as string), boardContent, boardImgUrl1, boardImgUrl2, boardImgUrl3, tag };

        axios.patch(PATCH_BOARD_URL, data, authorizationHeader(accessToken))
            .then((response) => patchBoardResponseHandler(response))
            .catch((error) => patchBoardErrorHandler(error))
    }

    const patchProduct = (product: Product) => {
        const data: PatchProductDto = { 
            ...product, 
            productNumber: product.productNumber as number, 
            boardNumber: Number(boardNumber as string)
        };

        axios.patch(PATCH_PRODUCT_URL, data, authorizationHeader(accessToken))
            .then((response) => patchProductResponseHandler(response))
            .catch((error) => patchProductErrorHandler(error))
    }

    // response handler //
    const boardImageUploadResponseHandler = (response: AxiosResponse<any, any>) => {
        const imageUrl = response.data as string;
        if (!imageUrl) return;
        setBoardImgUrl1(imageUrl);
    }
    const boardImageUploadResponseHandler2 = (response: AxiosResponse<any, any>) => {
        const imageUrl2 = response.data as string;
        if (!imageUrl2) return;
        setBoardImgUrl2(imageUrl2);
    }
    const boardImageUploadResponseHandler3 = (response: AxiosResponse<any, any>) => {
        const imageUrl3 = response.data as string;
        if (!imageUrl3) return;
        setBoardImgUrl3(imageUrl3);
    }

    const getBoardResponseHandler = (response: AxiosResponse<any, any>) => {
      const { result, message, data } = response.data as ResponseDto<GetBoardResponseDto>; 
      if (!result || !data) {
        alert(message);
        navigator('/');
        return;
      }

      const { boardContent, boardImgUrl1, boardImgUrl2, boardImgUrl3, tag, writerEmail } = data.board;

      if (writerEmail !== user?.email) {
        alert('권한이 없습니다');
        navigator('/');
        return;
      }
      setBoardContent(boardContent);
      setBoardImgUrl1(boardImgUrl1);
      if (boardImgUrl2) setBoardImgUrl2(boardImgUrl2);
      if (boardImgUrl3) setBoardImgUrl3(boardImgUrl3);
      setTag(tag);

      data.productList.forEach((product, index) => {
        if (index === 0) setProduct1(product);
        if (index === 1) setProduct2(product);
        if (index === 2) setProduct3(product);
        if (index === 3) setProduct4(product);
        if (index === 4) setProduct5(product);
        if (index === 5) setProduct6(product);
      })
    }
    
    const patchBoardResponseHandler = (response: AxiosResponse<any, any>) => {
      const { result, message, data } = response.data as ResponseDto<PatchBoardResponseDto>; 
      if (!result || !data) {
        alert(message);
        return;
      }
      navigator(`/board/detail/${boardNumber}`)
    }

    const patchProductResponseHandler = (response: AxiosResponse<any, any>) => {
      const { result, message, data } = response.data as ResponseDto<PatchProductResponseDto>; 
      if (!result || !data) {
        alert(message);
        return;
      } 
      navigator(`/product/${productNumber}`)
    }

    // error handler //
    const boardImageUploadErrorHandler = (error: any) => {
        console.log(error.message);
    }
    const patchBoardErrorHandler = (error: any) => {
      console.log(error.message);
  }
    const patchProductErrorHandler = (error: any) => {
        console.log(error.message);
    }
    const getBoardErrorHandler = (error: any) => {
        console.log(error.message);
    }

    // use effect //
    useEffect(() => {
        if(!boardNumber) {
            navigator('/');
            return;
        }
        if(!accessToken) {
            navigator('/')
            return;
        }
        if (user) getBoard();
    },[user]);

    return (
        <Box sx={{ paddingTop: '100px' }}>
            {/* //? 게시물 본문 */}
            <Box sx={{ width: '100%', display: 'block', textAlign: 'center' }}>
                <Box sx={{ p: '15px 0' }}>
                    <Box sx={{ width: '100%' }} >
                        <Box sx={{ width: '50%' }} component='img' src={boardImgUrl1} />
                    </Box>
                    <Box sx={{}}>
                        <IconButton onClick={() => onBoardImageUploadButtonHandler()} >
                            <AddAPhotoIcon />
                            <input ref={imageRef} hidden type='file' accept='image/*' onChange={(event) => onBoardImageUploadChangeHandler(event)} />
                        </IconButton>
                    </Box>
                </Box>
                <Divider sx={{ m: '40px 0' }} />
                <Box sx={{ p: '15px 0' }}>
                    <Box sx={{ width: '100%' }} >
                        <Box sx={{ width: '50%' }} component='img' src={boardImgUrl2} />
                    </Box>
                    <Box sx={{}}>
                        <IconButton onClick={() => onBoardImageUploadButtonHandler2()} >
                            <AddAPhotoIcon />
                            <input ref={imageRef2} hidden type='file' accept='image/*' onChange={(event) => onBoardImageUploadChangeHandler2(event)} />
                        </IconButton>
                    </Box>
                </Box>
                <Divider sx={{ m: '40px 0' }} />
                <Box sx={{ p: '15px 0' }}>
                    <Box sx={{ width: '100%' }} >
                        <Box sx={{ width: '50%' }} component='img' src={boardImgUrl3} />
                    </Box>
                    <Box sx={{}}>
                        <IconButton onClick={() => onBoardImageUploadButtonHandler3()} >
                            <AddAPhotoIcon />
                            <input ref={imageRef3} hidden type='file' accept='image/*' onChange={(event) => onBoardImageUploadChangeHandler3(event)} />
                        </IconButton>
                    </Box>
                </Box>
                <Divider sx={{ m: '40px 0' }} />
                <Box sx={{ display: 'block-flex', justifyContent: 'center', mt: '45px', ml: '225px', p: '15px 0px', width: '70%', border: 0.3, borderRadius: 0.5, backgroundColor: 'rgba(0, 0, 0, 0.02)' }}>
                    {/* //? 스타일 태그 */}
                    <Typography sx={{ m: '4px 10px 0 20px' }} >스타일 :</Typography>
                    <Input disableUnderline sx={{ mr: '10px', border: 0.05, width: '130px', height: '25px' }} value={tag} onChange={(event) => setTag(event.target.value)} />
                    {/* //? 본문 내용 입력 */}
                    <Input sx={{ width: '800px' }} value={boardContent} minRows={12} fullWidth multiline disableUnderline placeholder='내용을 입력하세요'
                        onChange={(event) => setBoardContent(event.target.value)} />
                </Box>
            </Box>

            <Divider sx={{ m: '40px 0' }} />

            <Fab sx={{ position: 'fixed', bottom: '150px', right: '100px' }} onClick={() => onUpdateHandler()}>
                <CreateIcon />
            </Fab>
        </Box>     
 )

    // todo : //
}