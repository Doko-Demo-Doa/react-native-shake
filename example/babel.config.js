const path = require('path');
const pkg = require('../package.json');

const root = path.resolve(__dirname, '..');
const src = path.join(root, pkg.source ?? 'src');

module.exports = function (api) {
  api.cache(true);

  return {
    presets: ['babel-preset-expo'],
    overrides: [
      {
        // Use a function instead of a string to avoid Babel's "no filename" error
        // when Metro calls transform without a filename for internal virtual modules.
        include: (filename) => Boolean(filename && filename.startsWith(src)),
        presets: [
          [
            require.resolve('react-native-builder-bob/babel-preset'),
            { supportsStaticESM: true },
          ],
        ],
      },
    ],
  };
};
