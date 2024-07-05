package gift.controller;

import gift.dto.wish.WishCreateRequestDTO;
import gift.dto.wish.WishRequestDTO;
import gift.dto.wish.WishResponseDTO;
import gift.service.MemberService;
import gift.service.WishService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wishes")
public class WishController {
    private final WishService wishService;
    private final MemberService memberService;

    public WishController(WishService wishService, MemberService memberService) {
        this.wishService = wishService;
        this.memberService = memberService;
    }

    private Long getMemberIdFromRequest(HttpServletRequest request) {
        memberService.validateToken(request);
        return (Long) request.getAttribute("memberId");
    }

    @GetMapping
    public ResponseEntity<List<WishResponseDTO>> getWishlist(HttpServletRequest request) {
        Long memberId = getMemberIdFromRequest(request);
        List<WishResponseDTO> wishlist = wishService.getWishlistByMemberId(memberId);
        return ResponseEntity.ok(wishlist);
    }

    @PostMapping
    public ResponseEntity<WishResponseDTO> addWish(@Valid @RequestBody WishCreateRequestDTO wishRequestDTO, HttpServletRequest request) {
        Long memberId = getMemberIdFromRequest(request);
        WishRequestDTO wishWithMemberId = new WishRequestDTO(memberId, wishRequestDTO.productId());
        WishResponseDTO createdWish = wishService.addWish(wishWithMemberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWish);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWish(@PathVariable Long id) {
        wishService.deleteWish(id);
        return ResponseEntity.noContent().build();
    }
}
