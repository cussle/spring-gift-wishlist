package gift.controller;

import gift.dto.member.MemberRequestDTO;
import gift.dto.member.MemberResponseDTO;
import gift.dto.product.ProductRequestDTO;
import gift.dto.product.ProductResponseDTO;
import gift.model.Member;
import gift.service.MemberService;
import gift.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final ProductService productService;
    private final MemberService memberService;

    public AdminController(ProductService productService, MemberService memberService) {
        this.productService = productService;
        this.memberService = memberService;
    }

    @GetMapping("")
    public String adminHome(Model model) {
        model.addAttribute("message", "Welcome to the Admin Panel!");
        return "admin";
    }

    // 상품 관리
    @GetMapping("/products")
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @GetMapping("/products/new")
    public String showAddProductForm(Model model) {
        model.addAttribute("product", new ProductResponseDTO(null, "", 0, ""));
        return "product_form";
    }

    @PostMapping("/products")
    public String addProduct(@Valid @ModelAttribute("product") ProductRequestDTO productDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "product_form";
        }
        productService.addProduct(productDTO);
        return "redirect:/admin/products";
    }

    @GetMapping("/products/{id}/edit")
    public String showEditProductForm(@PathVariable("id") Long id, Model model) {
        ProductResponseDTO productDto = productService.getProductById(id);
        model.addAttribute("product", productDto);
        return "product_edit";
    }

    @PutMapping("/products/{id}")
    public String updateProduct(@PathVariable("id") Long id, @Valid @ModelAttribute ProductRequestDTO productDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("product", productDTO);
            model.addAttribute("org.springframework.validation.BindingResult.product", result);
            return "product_edit";
        }
        productService.updateProduct(id, productDTO);
        return "redirect:/admin/products";
    }

    @DeleteMapping("/products/{id}")
    public String deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }


    // 회원 관리
    @GetMapping("/members")
    public String getAllMembers(Model model) {
        model.addAttribute("members", memberService.getAllMembers());
        return "members";
    }

    @GetMapping("/members/new")
    public String showAddMemberForm(Model model) {
        model.addAttribute("member", new MemberRequestDTO(null, "", ""));
        return "member_form";
    }

    @PostMapping("/members")
    public String addMember(@Valid @ModelAttribute("member") MemberRequestDTO memberDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "member_form";
        }
        memberService.registerMember(memberDTO);
        return "redirect:/admin/members";
    }

    @GetMapping("/members/{id}/edit")
    public String showEditMemberForm(@PathVariable("id") Long id, Model model) {
        MemberResponseDTO memberDto = memberService.getMemberById(id);
        model.addAttribute("member", new Member(memberDto.id(), memberDto.email(), null));
        return "member_edit";
    }

    @PutMapping("/members/{id}")
    public String updateMember(@PathVariable("id") Long id, @Valid @ModelAttribute MemberRequestDTO memberDTO, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("member", memberDTO);
            model.addAttribute("org.springframework.validation.BindingResult.member", result);
            return "member_edit";
        }
        memberService.updateMember(id, memberDTO);
        return "redirect:/admin/members";
    }

    @DeleteMapping("/members/{id}")
    public String deleteMember(@PathVariable("id") Long id) {
        memberService.deleteMember(id);
        return "redirect:/admin/members";
    }
}
