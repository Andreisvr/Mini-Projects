

const handleClick = () => {
    // Calculate the position to scroll to (current scroll position + 20vh)
    const scrollToPosition = window.pageYOffset + window.innerHeight * 1;
    
    // Scroll the window to the calculated position
    window.scrollTo({
        top: scrollToPosition,
        behavior: 'smooth' // Add smooth scrolling effect
    });
};

export default handleClick;