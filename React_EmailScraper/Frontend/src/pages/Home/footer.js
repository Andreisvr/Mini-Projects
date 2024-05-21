import React from "react";

export default function FooterSmall(props) {
  return (
    <>
      <footer
        className="w-full pb-6"
        style={{ backgroundColor: '#e6f2f8', width:'100%', bottom: props.absolute ? '0' : 'auto' }}
      >
       <div style={{ width: '100%' }} className="w-full px-4">
          <hr className="mb-6 border-b-1 border-blueGray-600 w-full" />
          <div className="flex items-center justify-end">
            <a href="/contact" className="text-white hover:text-blueGray-300 text-sm font-semibold block py-1 px-3">Contact</a>
            <span className="mx-10"></span> {/* Spațiu între elemente /}
            <a href="/help" className="text-white hover:text-blueGray-300 text-sm font-semibold block py-1 px-3">Help</a>
            <span className="mx-10"></span> {/ Spațiu între elemente /}
            <a href="/scrape" className="text-white hover:text-blueGray-300 text-sm font-semibold block py-1 px-3">Scrape</a>
            <span className="mx-10"></span> {/ Spațiu între elemente */}
            <a href="/login" className="text-white hover:text-blueGray-300 text-sm font-semibold block py-1 px-3">Log In</a>
          </div>
          <div className="text-right mt-6">
          <div style={{ padding: '2vh' }}></div>
            <div className="text-sm text-blueGray-500 font-semibold py-1">
              Copyright © {new Date().getFullYear()} MailHarvest
            </div>
            <div style={{ padding: '2vh' }}></div>
          </div>
        </div>
      </footer>
    </>
  );
}